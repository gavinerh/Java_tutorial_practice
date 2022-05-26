from packages.classifyLogs import *
from packages.fileManagement import *

uri = "http://localhost:9200/graylog_0/_search"
timeNow = "2022-05-17 07:00:00.000"
earlierTime = "2022-05-15 00:00:00.000"

fileManager = FileManagement()
searchQuery = fileManager.readFromFile("/home/docker/graylog/conf_files/query")
filepaths = fileManager.readFromFile("/home/docker/graylog/conf_files/filepaths.conf")
combinedPath = filepaths[0]
errorPath = filepaths[1]
sshd_path = filepaths[2]
brute_force_attacks = filepaths[3]
paths = [combinedPath, errorPath]

elasticsearch = QueryElasticsearch(uri, timeNow, earlierTime)
# clear file content in combined file and error file
for path in paths:
    fileManager.clearFileContent(path)
for query in searchQuery:
    generateLogs = GenerateLogFiles(query)
    generateLogs.generateFiles(elasticsearch, combinedPath, errorPath)

# filter the generated logs
filter = FilterInvalidUser()

# read jmpadm/sshd file
arr = fileManager.readFromFile(sshd_path)
catch_phrases = fileManager.readFromFile("/home/docker/graylog/conf_files/brute_force_catch_phrase")
mydict = filter.categorize_content(arr, catch_phrases)
confirmedAttacks = filter.compare_results(mydict, catch_phrases)
prev_brute_force_logs = fileManager.readFromFile(brute_force_attacks)
for attacks in confirmedAttacks:
    cleaned_response = elasticsearch.cleaned_objects(elasticsearch.search(attacks))
    combined = ""
    generateLogs = GenerateLogFiles()
    for i in range(len(cleaned_response)):
        cleaned = generateLogs.remove_process_ids(cleaned_response[i]['_source']['message'])
#        print(cleaned + '\t' + cleaned_response[i]['_source']['timestamp'])
        timestamp = cleaned_response[i]['_source']['timestamp'] + " +00:00"
        temp = cleaned + "\t" + timestamp
        # prevent duplicate logs
        if temp in prev_brute_force_logs:
            continue
        combined = combined + temp + "\n"
    fileManager.writeToFile('a', brute_force_attacks, combined)
