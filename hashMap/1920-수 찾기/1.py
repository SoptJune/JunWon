# 입력값
n = int(input())
arr1 = list(map(int,input().split()))

m = int(input())
arr2 = list(map(int,input().split()))

# arr안에 수 x가 있는지 없는지 판정하는 함수

def binary_search(arr,start,end,x):
    if start > end:
        print(0)
        return

    mid = (start + end) // 2

    if arr[mid] == x:
        print(1)
        return
    elif arr[mid] > x :
        return binary_search(arr,start,mid-1,x)

    elif arr[mid] < x:
        return binary_search(arr,mid+1, end, x)

arr1.sort() # 이분 탐색을 위한 필요조건: 정렬
start = 0
end = n-1

for i in arr2:
    binary_search(arr1,start,end,i)