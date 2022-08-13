import sys

input = lambda: sys.stdin.readline().rstrip()
write = lambda x: sys.stdout.write(str(x) + "\n")

def isPossible(x,y):
    for Q_y in range(y):
        Q_x = graph[Q_y]
        if Q_x == x or abs(x- Q_x) == y- Q_y:
            return False

    return True

def backTracking(y):
    global ans
    if y == n:
        ans += 1
        return

    for x in range(n):
        if isPossible(x,y):
            graph[y] = x
            backTracking(y+1)
            graph[y] = 0

    return
# 출력
n = int(input()) # 15
graph = [0] * n  # Q의 위치
ans = 0
backTracking(0)
print(ans)
