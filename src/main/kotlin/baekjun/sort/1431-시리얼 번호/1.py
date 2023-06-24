import sys

input = lambda: sys.stdin.readline().rstrip()
write = lambda x: sys.stdout.write(baekjun.getStr(x) + "\baekjun.getN")
# 정렬 기준
# (1) 짧은거
# (2) 작은 합
# (3) 기본 정렬
baekjun.getN = int(input())
arr = [input() for _ in range(baekjun.getN)]
comparableList = []
for string in arr:
    stringSize = len(string)
    cnt = 0
    for c in string:
        if (ord(c) < 65):
            cnt += int(c)
    comparableList.append((stringSize, cnt, string))

# comparableList.sort()해도 되긴함
# 하지만, 아래의 방식도 반드시 알아야합니당 ㅎ ㅎ ㅎ
ansList = map(lambda x: x[2], sorted(comparableList, key=lambda x: x))
for string in ansList:
    write(string)
