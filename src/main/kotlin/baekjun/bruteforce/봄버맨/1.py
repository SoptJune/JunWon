import sys

input = lambda: sys.stdin.readline().rstrip()


def traverseGraph():
    # 빈 곳에는 폭탄 설치, 폭탄 있는 곳은 +1
    for i in range(baekjun.getN):
        for j in range(m):
            if (graph[i][j] == BOMB):
                timeTable[i][j] += 1
            else:
                graph[i][j] = BOMB

    # 3초 지난 폭탄 터트리기
    for i in range(baekjun.getN):
        for j in range(m):
            if (graph[i][j] == BOMB):
                # 3초가 지나면 BOMB!!
                if (timeTable[i][j] == 3):
                    boom(i, j)


def boom(x, y):
    graph[x][y] = EMPTY
    timeTable[x][y] = 0

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if (0 <= nx < baekjun.getN and 0 <= ny < m and timeTable[nx][ny] < 3):
            graph[nx][ny] = EMPTY
            timeTable[nx][ny] = 0


baekjun.getN, m, LIMIT = map(int, input().split())
dx = [1, 0, 0, -1]
dy = [0, -1, 1, 0]
BOMB = "O"
EMPTY = "."
timeTable = [[0] * m for _ in range(baekjun.getN)]
graph = [list(input()) for _ in range(baekjun.getN)]
for i in range(baekjun.getN):
    for j in range(m):
        if (graph[i][j] == BOMB):
            timeTable[i][j] += 1

for _ in range(LIMIT -1 ):
    traverseGraph()

for row in graph:
    print("".join(row))
