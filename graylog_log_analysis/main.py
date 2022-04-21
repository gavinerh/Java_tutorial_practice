# import numpy as np
# import pandas as pd
#
# df = pd.read_csv("C:\\Users\\gavin\\OneDrive\\Desktop\\logFile\\logs.csv")
# analysis = df.describe()
# print(analysis.loc["unique"]["Message"])


import datetime
from datetime import timedelta

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
    nowStr = generateTimeStr(now)
    past = generateTimeStr(past)

    return [nowStr, past]
arr = generateTimeRange()
print(arr[0] + "\n")
print(arr[1])
print(type(arr))