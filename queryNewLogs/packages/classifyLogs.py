from packages.queryElasticsearch import QueryElasticsearch
from packages.fileManagement import FileManagement
import re

class GenerateLogFiles:
    def __init__(self, searchQuery=""):
        self.searchQuery = searchQuery

    # create classification buckets by extracting folder and filename
    def createBuckets(self, responseStr):
        arr = responseStr.split(' ')
        arr = responseStr.split(' ')
        folder = arr[0]
        arr[1] = arr[1][:-1]
        file = arr[1]
        return "/home/docker/graylog/logfiles/" + folder + "/" + file

    # populate response logs in dictionary
    def populateDictionary(self, dict, response, filename):
        if(filename not in dict):
            dict[filename] = []
        dict[filename].append(response)
        return dict

    def remove_process_ids(self, response):
         # remove process id in []
         response = re.sub('\[(.*?)\]', '', response)
         # remove process id in ()
         response = re.sub('\(.[0-9]*?\)', '', response)
         return response

    # sort response into individual buckets
    def createCSVString(self, response):
        index = 0
        # contain the filenames as the key
        myDict = {}
        while index < len(response):
            response[index] = response[index]['_source']['message']
            response[index] = self.remove_process_ids(response[index])
            filename = self.createBuckets(response[index])
            myDict = self.populateDictionary(myDict, response[index], filename)
            index+=1
        return myDict

    # cleanse logs of certain less important information
    def remove_unimportant(self, log):
        chars = ["Message of the Day", "Day", "Finished Cleanup of Temporary Directories", "Daily", "daily", "anacron"]
        contains_char = False
        for char in chars:
            if char in log:
                contains_char = True
                break
        return contains_char

    # remove duplicates
    def cleanse_logs(self, mydict, k):
        newDict = {}
        for s in mydict[k]:
            # remove logs containing less important information
            if self.remove_unimportant(s):
                continue
            if s not in newDict:
                newDict[s] = 1
        return newDict

    def writeToFile(self, path, newDict, writeMethod, errorPath):
        f = None
        f1 = None
        try:
            f = open(path, writeMethod)
            for s in newDict.keys():
                f.write(s)
                f.write('\n')
        except:
            f1 = open(errorPath, 'a')
            for s in newDict.keys():
                f1.write("Path: " + path + "\n")
                f1.write("Message: " + s + "\n")
        finally:
            if f is not None:
                f.close()
            if f1 is not None:
                f1.close()


    def dumpToFiles(self, mydict, path, errorPath):
        for k in mydict.keys():
            newDict = self.cleanse_logs(mydict, k)
            # write to individual categorical file
            self.writeToFile(k, newDict, 'w', errorPath)
            # write to combined file
            self.writeToFile(path, newDict, 'a', errorPath)
        return

    # combination method to generate the files
    def generateFiles(self, elasticsearch, combinedPath, errorPath):
        # elasticsearch object is initialised in calling function
        response = elasticsearch.search(self.searchQuery)
        cleaned_response = elasticsearch.cleaned_objects(response)
        print("Returned response length for " + self.searchQuery + ": " + str(len(cleaned_response)))
        # preprocess response string and generate dict of filenames as key
        mydict = self.createCSVString(cleaned_response)
        print(len(mydict))
        self.dumpToFiles(mydict, combinedPath, errorPath)


class FilterInvalidUser:
    # categorise the file content and compare
    def categorize_content(self, arr, catch_phrases):
        # dictionary will separate the logs into 2 categories,
        # first: invalid user, second: maximum authentication attempts exceeded
        mydict = {}
        for element in arr:
            for phrase in catch_phrases:
                if phrase not in mydict:
                    mydict[phrase] = []
                if phrase in element:
                    result = element
                    if phrase == catch_phrases[0]:
                        result = element.split('user ')[1]
                    mydict[phrase].append(result)
        return mydict

    # compare result
    def compare_results(self, mydict, catch_phrases):
        arr1 = mydict[catch_phrases[0]]
        arr2 = mydict[catch_phrases[1]]
        confirmedAttacks = []
        # compare arr1 to arr2
        for i in arr1:
            for j in arr2:
                if i in j:
                    confirmedAttacks.append(i)
        return confirmedAttacks
