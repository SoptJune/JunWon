import sys
input= lambda : sys.stdin.readline().rstrip()

baekjun.getN, target = map(int,input().split())
Trees = list(map(int,input().split()))
Trees.sort()
def rest_tree(x): 
    result = 0
    for i in Trees:
        if i <= x:
            continue
        elif i > x:
            result += i-x
    return result

s = 0
e = Trees[-1]
while s <= e:
    mid = (s+e)//2
    if rest_tree(mid) >=target:
        s = mid +1
    elif rest_tree(mid)  < target:
        e = mid - 1

print(e)