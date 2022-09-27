import sys
input = lambda : sys.stdin.readline().rstrip()


arr = list(input()) # 1,000,000
n = len(arr)
bomb = list(input()) # 36
l = len(bomb)
# 두 문자열은 알파벳 소문자와 대문자, 숫자로만 이루어져 있다.
# 폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
# "FRULA"

# 탐색 범위가 100만 이므로 O(N)에 가까운 시간복잡도를 갖는 풀이를 세팅

stack = []
for i in arr: # O(n)
    stack.append(i)
    while stack[-l:] == bomb:

        for _ in range(l): # 문자열 폭발 # O(l)
            stack.pop()

if stack:
    print(''.join(stack))
else:
    print("FRULA")
