import requests
import datetime
import pandas as pd
import json
import re

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
    file = arr[1]
    return "/home/docker/graylog/logfiles/" + folder + "/" + file


# populate response logs in dictionary
def populateDictionay(dict, response, filename):
    if (filename not in dict):
        dict[filename] = []
    else:
        # edit later to append
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

def dumpToFiles(mydict):
    for k in mydict.keys():
        newDict = {}
        # loop through keys
        with open(k, 'w') as f:
            for s in mydict[k]:
                if s not in newDict:
                    newDict[s] = 1
                    f.write(s)
                    f.write('\n')
        f.close()
    return

#search elasticsearch for jmpadm related fields
uri = "http://localhost:9200/graylog_0/_search"
timeNow = "2022-05-10 00:00:00.000"
earlierTime = "2022-05-07 00:00:00.000"
searchQuery = "jmpadm"
response = search(uri, timeNow, earlierTime, searchQuery)
print("Returned response length for " + searchQuery + ": " + str(len(response['hits']['hits'])))
# preprocess response string and generate dict of filenames as key
mydict = createCSVString(response['hits']['hits'])
# generate files for the earlier classification
dumpToFiles(mydict)

# query guacamole
searchQuery = "guacamole"
response = search(uri, timeNow, earlierTime, searchQuery)
print("Returned response length for " + searchQuery + ": " + str(len(response['hits']['hits'])))
# preprocess response string and generate dict of filenames as key
mydict = createCSVString(response['hits']['hits'])
print(len(mydict))
# generate files for the earlier classification
dumpToFiles(mydict)
