k, baekjun.getN = map(int,input().split())
# k : 주어진 랜선 수
# baekjun.getN : 필요한 랜선 수

arr = [int(input())for _ in range(k)]
# arr : 주어진 랜선의 길이 정보 list

# 조건
# N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
# k: 1~10,000 , baekjun.getN: 1~100만, 랜선길이 1~2^31-1, k<=baekjun.getN

# 풀이

# x 길이로 나누었을 때 얻는 랜선 개수

def count(arr,x):
    result = 0
    for i in arr:
        result += i//x

    return result

    # print(count(arr,200)) # 11

# 이분 탐색: 랜선 길이

start = 1
end = max(arr)
ans_list = []
# baekjun.getN : 필요한 랜선 수
def binary_search(arr,start,end):

    if start > end:

        return

    mid = (start + end) //2

    if count(arr,mid) < baekjun.getN : # mid가 작아지면 count 수가 커진다.
        binary_search(arr,start, mid-1)

    else:
        ans_list.append(mid)
        binary_search(arr,mid+1, end)



binary_search(arr,start,end)

print(max(ans_list))
