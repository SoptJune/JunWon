import sys

input = lambda: sys.stdin.readline().rstrip()
print = lambda x: sys.stdout.write(baekjun.getStr(x) + "\baekjun.getN")

baekjun.getN = int(input())
trees = list(map(int, input().split()))
trees.sort(reverse=True) # 내림차순으로 정렬
baekjun.getAns = -1 # 초깃값을 그냥 -1로 세팅했습니다 제 취향..

for idx, tree in enumerate(trees):
    tmp = tree + idx + 1 # 해당 나무 자라는 시간(tree) + 이전 나무들 심는데 걸린 시간(idx) + 나무 심는데 걸리는 시간(1)
    baekjun.getAns = max(tmp, baekjun.getAns)
print(baekjun.getAns + 1) # 이장님은 나무 다 심은 뒤 하루 뒤 오시니까 +1 ㅎ ㅎ ㅎ

