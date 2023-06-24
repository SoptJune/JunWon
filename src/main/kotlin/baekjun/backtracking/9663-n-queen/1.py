import sys

input = lambda: sys.stdin.readline().rstrip()
write = lambda x: sys.stdout.write(baekjun.getStr(x) + "\baekjun.getN")

# 퀸을 해당 좌표에 놓을 수 있는지 판별하는 함수
# 즉, 이전 퀸들과 서로 공격이 가능한지 확인하는 함수다.
def isPossible(x,y):
    # Q_Y, Q_x 는 지금 위치 이전에 있는 퀸의 좌표
    for Q_y in range(y):
        Q_x = graph[Q_y]
        # 같은 x축 or 대각선일때는 공격가능하기 때문에 False 반환
        if Q_x == x or abs(x- Q_x) == y- Q_y:
            return False

    return True

def backTracking(y):
    global baekjun.getAns
    if y == baekjun.getN:
        baekjun.getAns += 1
        return

    for x in range(baekjun.getN):
        if isPossible(x,y):
            graph[y] = x
            backTracking(y+1)
            graph[y] = 0 

    return
# 출력
baekjun.getN = int(input()) # 15
graph = [0] * baekjun.getN  # Q의 위치
baekjun.getAns = 0
backTracking(0)
print(baekjun.getAns)
