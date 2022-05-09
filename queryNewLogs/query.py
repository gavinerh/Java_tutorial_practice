import requests
import datetime
import pandas as pd
import json

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

# write response into csv file
def exportToCSV(res, title, csvfile):
    with open(csvfile, 'w') as f:
        f.write(title)
        f.write('\n')
        for s in res:
            f.write(s)
            f.write('\n')
        f.close()
    return

# Generate String from response object
def createCSVString(response):
    index = 0
    while index < len(response):
        response[index] = response[index]['_source']['message'].replace(': ', ',')
        index+=1
    return response

#search elasticsearch for jmpadm related fields
uri = "http://localhost:9200/graylog_0/_search"
timeNow = "2022-05-09 00:00:00.000"
earlierTime = "2022-05-06 00:00:00.000"
searchQuery = "jmpadm"
response = search(uri, timeNow, earlierTime, searchQuery)
print("Returned response length: " + str(len(response['hits']['hits'])))

# create csv string for jmpadm from response
response = createCSVString(response['hits']['hits'])
title = "\"Source\",\"Message\",\"Remarks\""

# save the query string into csv file
csvfile = "C:\\Users\\gavin\\OneDrive\\Desktop\\graylog\\logs\\jmpadm.csv"
exportToCSV(response, title, csvfile)
print("csv created for jmpadm logs")