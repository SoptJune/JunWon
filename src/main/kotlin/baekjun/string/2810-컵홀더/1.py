import sys

input = lambda: sys.stdin.readline().rstrip()
print = lambda x: sys.stdout.write(baekjun.getStr(x) + "\baekjun.getN")

baekjun.getN = int(input())
cnt = 1  # 처음 좌석 왼쪽에 컵홀더가 1개 있으니 1부터 시작
# 컵 홀더의 수는 좌석의 오른쪽만 세도록하자
seats = input()
idx = 0
while (idx < baekjun.getN):
    if (seats[idx] == 'S'):
        cnt += 1
        idx += 1
    else:
        cnt += 1
        idx += 2
print(baekjun.getN if baekjun.getN < cnt else cnt)

