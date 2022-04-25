import json

import requests
import datetime
import pandas as pd

# Query the elasticsearch api
def search(uri, timeNow, earlierTime):
    query = json.dumps({
        "size":10000,
        "query" : {
            "bool": {
                "must": [
                    {
                        "match_phrase": {
                            "message": "ET SCAN Suspicious inbound"
                        }
                    },
                    {
                        "range" : {
                            "timestamp": {
                                "gt": earlierTime,
                                "lt": timeNow
                            }
                        }
                    }
                ]
            }
        }
    })
    # print(query)
    response = requests.get(uri, data=query, headers={"Content-Type":"application/json"})
    results = json.loads(response.text)
    return results

# Convert datetime object to a string
def generateTimeStr(time):
    timeStr = str(time)
    arr = timeStr.split(".")
    truncated = arr[0]
    truncated += ".000"
    return truncated

# Generate time range to query the elasticsearch api
def generateTimeRange():
    now = datetime.datetime.now()
    diff = datetime.timedelta(minutes=10)
    past = now - diff
    now = generateTimeStr(now)
    past = generateTimeStr(past)
    return [now, past]

# Export response string to a csv
def exportToCSV(res, title, csvfile):
    with open(csvfile, 'w') as f:
        f.write(title)
        f.write('\n')
        for s in res:
            f.write(s)
            f.write('\n')
        f.close()
    return
# Generate string from response object
def createCSVString(response):
    index = 0
    while index < len(response):
        timestamp = response[index]['_source']['timestamp']
        message = response[index]['_source']['message'].replace('] ', ',')
        response[index] = message.replace('-> ', ',')
        response[index] += ','
        response[index] += timestamp
        index+=1
    return response

# Checks if current collected logs are within the classification identified
def checkSimilar(arr, log):
    if(len(arr) == 0):
        return None
    count = 0
    for s in arr:
        if s == log:
           count += 1
    return count != 0

# Reads the stored classification file
def readStoredClassification(classificationFile):
    content = ""
    # if the csv is not empty continue with reading the stored classification file
    try:
        with open(classificationFile) as f:
            content = f.readlines()
    except FileNotFoundError:
        print("File not found or created yet")
        return []
    arr = []
    for line in content:
        arr.append(line)
    return arr

# Save new messages into classification file
def populateClassificationFile(messages, classificationFile):
    with open(classificationFile, 'w') as f:
        for s in messages:
            f.write(s)
            f.write('\n')
        f.close()

# Create email notification
def generateEmailNotification(messages):
    content = "";
    for i in range(len(messages)):
        content += "\n"
        content += str(i+1) + ". "
        content += messages[i]
        content += "\n"
    url = "http://localhost:8080/mail"
    time = generateTimeStr(datetime.datetime.now())
    query = {
        "datetime":time,
        "content":content
    }
    response = requests.post(url=url, json=query)
    print(response)

# Analyse the csv to generate unique messages from message column
def analyse(csvfile, classificationFile):
    df = pd.read_csv(csvfile)
    if(len(df) == 0):
        return
    messages = df["Message"].unique()
    # get stored classification
    fileContentArr = readStoredClassification(classificationFile)
    if len(fileContentArr) == 0:
        populateClassificationFile(messages, classificationFile)
        generateEmailNotification(messages)
        # the fresh csv file should not be empty at this stage
        # for now, we will add it to the stored classification file, and trigger email
        # later we will trigger email, then let user read email and trigger
        # storage of new classification
    else:
        # storedClassification is not empty, we do the comparison using checkSimilar
        # use for loop to input the logs into checkSimilar function, if False is returned
        # trigger email and storage,
        # if true returned, then ignore and return
        # storage of new classification
        toStore = []
        for s in messages:
            if checkSimilar(fileContentArr, s) != True:
                toStore.append(s)
        populateClassificationFile(toStore)
        generateEmailNotification(toStore)
        print("New messages stored")
    return

# query elasticsearch using a set timerange
url = "http://localhost:9200/graylog_0/_search"
timeRange = generateTimeRange()
print(timeRange[0] + "\n" + timeRange[1])
response = search(url, timeRange[0], timeRange[1])
print("Returned response length: " + str(len(response['hits']['hits'])))

# create csv string from response
csvfile = "/home/docker/graylog/logfiles/logs.csv"
response = createCSVString(response['hits']['hits'])
title = "\"Source\",\"Message\",\"Priority\",\"Source IP\",\"Destination IP\",\"Timestamp\""

# save the query string into csv file
exportToCSV(response, title, csvfile) # remove unformatted lines
print("Csv created")

# analyse the csv file using pandas library
classificationFile = "/home/docker/graylog/logfiles/classificationFile.txt"
analyse(csvfile, classificationFile)
