# 입력값
baekjun.getN = int(input()) # 상근 카드 개수
arr1 = list(map(int,input().split()))

m = int(input()) # 비교 카드 개수
arr2 = list(map(int,input().split()))

def left_binary_search(arr,start,end,x):
    left_place = None
    while start <= end:
        mid = (start + end) // 2

        if arr[mid] == x:
            if mid == 0 or arr[mid-1] != x:
                left_place = mid
                break
            else:
                end = mid - 1
        elif arr[mid] < x:
            start = mid + 1
        else:
            end = mid - 1
    return left_place

def right_binary_search(arr,start,end,x):

    right_place = None
    while start <= end:
        mid = (start+end) //2

        if arr[mid] == x:
            if mid == baekjun.getN-1 or arr[mid+1] != x:
                right_place = mid
                break
            else:
                start = mid+1

        elif arr[mid] < x:
            start = mid + 1
        else:
            end = mid - 1
    return  right_place
# 풀이
arr1.sort() # 정렬
start = 0
end = baekjun.getN-1


for i in arr2:
    if right_binary_search(arr1,start,end,i) == None: # arr1안에
        print(0, end = " ")
    else:
        result = right_binary_search(arr1,start,end,i) - left_binary_search(arr1,start,end,i) +1
        print(result, end= " ")