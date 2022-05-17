import requests
import datetime
import pandas as pd
import json
import re
import os

# Query elasticsearch api
def search(uri, timeNow, earlierTime, searchQuery):
    query = json.dumps({
        "size":10000,
        "query" : {
            "bool": {
                "must": [
                    {
                        "match_phrase": {
                            "message": searchQuery
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
    response = requests.get(uri, data=query, headers={"Content-Type":"application/json"})
    results = json.loads(response.text)
    return results

# create classification buckets
def createBuckets(responseStr):
    # extract the folder and file name
    arr = responseStr.split(' ')
    folder = arr[0]
    arr[1] = arr[1][:-1]
    file = arr[1]
    return "/home/docker/graylog/logfiles/" + folder + "/" + file


# populate response logs in dictionary
def populateDictionay(dict, response, filename):
    if (filename not in dict):
        dict[filename] = []
    dict[filename].append(response)
    return dict

# sort response into individual buckets
def createCSVString(response):
    index = 0
    # contain the filenames as the key
    myDict = {}
    while index < len(response):
        response[index] = response[index]['_source']['message']
        # remove process id in []
        response[index] = re.sub('\[(.*?)\]', '', response[index])
        # remove process id in ()
        response[index] = re.sub('\(.[0-9]*?\)', '', response[index])
        filename = createBuckets(response[index])
        myDict = populateDictionay(myDict, response[index], filename)
        index+=1
    return myDict

# cleanse logs of certain less important information
def remove_unimportant(log):
    chars = ["Message of the Day", "Day", "Finished Cleanup of Temporary Directories", "Daily", "daily", "anacron"]
    contains_char = False
    for char in chars:
        if char in log:
            contains_char = True
            break
    return contains_char

# remove duplicates
def cleanse_logs(mydict, k):
    newDict = {}
    for s in mydict[k]:
        # remove logs containing less important information
        if remove_unimportant(s):
            continue
        if s not in newDict:
            newDict[s] = 1
    return newDict

# write to file
def writeToFile(path, newDict, writeMethod):
    f = None
    f1 = None
    try:
        f = open(path, writeMethod)
        for s in newDict.keys():
            f.write(s)
            f.write('\n')
    except:
        f1 = open("/home/docker/graylog/logfiles/error", 'a')
        for s in newDict.keys():
            f1.write("Path: " + path + "\n")
            f1.write("Message: " + s + "\n")
    finally:
        if f is not None:
            f.close()
        if f1 is not None:
            f1.close()

def dumpToFiles(mydict):
    path = "/home/docker/graylog/logfiles/combined/logs-all"
    for k in mydict.keys():
        newDict = cleanse_logs(mydict, k)
        # write to individual categorical file
        writeToFile(k, newDict, 'w')
        # write to combined file
        writeToFile(path, newDict, 'a')
    return

#search elasticsearch for jmpadm related fields
uri = "http://localhost:9200/graylog_0/_search"
timeNow = "2022-05-17 07:00:00.000"
earlierTime = "2022-05-15 00:00:00.000"
searchQuery = "jmpadm"
response = search(uri, timeNow, earlierTime, searchQuery)
print("Returned response length for " + searchQuery + ": " + str(len(response['hits']['hits'])))
# preprocess response string and generate dict of filenames as key
mydict = createCSVString(response['hits']['hits'])
#print(mydict)
# generate files for the earlier classification
dumpToFiles(mydict)

# query guacamole
# check for exception when using configuration files in future
searchQuery = "guacamole"
response = search(uri, timeNow, earlierTime, searchQuery)
print("Returned response length for " + searchQuery + ": " + str(len(response['hits']['hits'])))
# preprocess response string and generate dict of filenames as key
mydict = createCSVString(response['hits']['hits'])
# print(len(mydict))
# generate files for the earlier classification
dumpToFiles(mydict)
