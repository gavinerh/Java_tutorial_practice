import json

import requests
import datetime
import pandas as pd

# function to query the elasticsearch api
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

def generateTimeStr(time):
    timeStr = str(time)
    arr = timeStr.split(".")
    truncated = arr[0]
    truncated += ".000"
    return truncated

def generateTimeRange():
    now = datetime.datetime.now()
    diff = datetime.timedelta(minutes=5)
    past = now - diff
    now = generateTimeStr(now)
    past = generateTimeStr(past)
    return [now, past]

def exportToCSV(res, title):
    with open('/home/docker/graylog/logfiles/logs.csv', 'w') as f:
        f.write(title)
        f.write('\n')
        for s in res:
            f.write(s)
            f.write('\n')
        f.close()
    return

def createCSVString(response):
    index = 0;
    while index < len(response):
        timestamp = response[index]['_source']['timestamp']
        message = response[index]['_source']['message'].replace('] ', ',')
        response[index] = message.replace('-> ', ',')
        response[index] += ','
        response[index] += timestamp
        index+=1
    return response

# function that checks if current collected logs are within the classification identified
def checkSimilar(arr, log):
    if(len(arr) == 0):
        return None
    count = 0
    for s in arr:
        if s == log:
           count += 1
    return count != 0

# function that reads the stored classification file
def readStoredClassification():
    content = ""
    # if the csv is not empty continue with reading the stored classification file
    with open("/home/docker/graylog/logfiles/log_classification.txt") as f:
        content = f.readlines()
    arr = []
    for line in content:
        arr.append(line)
    return arr

# function that analyse the csv to generate unique messages from message column
def analyse():
    df = pd.read_csv('/home/docker/graylog/logfiles/logs.csv')
    if(len(df) == 0):
        return
    # get stored classification
    arr = readStoredClassification()
    if len(arr) == 0:
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




# query elasticsearch using a set timerange
url = "http://localhost:9200/graylog_0/_search"
timeRange = generateTimeRange()
response = search(url, timeRange[0], timeRange[1])
print("Returned response length: " + str(len(response['hits']['hits'])))

# create csv string from response
response = createCSVString(response['hits']['hits'])
# print(response[0])
title = "\"Source\",\"Message\",\"Priority\",\"Source IP\",\"Destination IP\",\"Timestamp\""

# save the query string into csv file
exportToCSV(response[:-10], title) # remove unformatted lines
print("Csv created")

# analyse the csv file using pandas library
