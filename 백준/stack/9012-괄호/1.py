n = int(input())
for i in range(n):
    string = input()
    sum = 0
    for c in string:
        if c == "(":
            sum += 1
        elif c == ")":
            sum -= 1
        if sum < 0: ## 만약 ")"가 남아있다면 뒤에 어떤 문자가 오던지 괄호를 없애는 건 불가능하다 
            print("NO")
            break
    if sum == 0:
        print("YES")
    elif sum > 0:
        print("NO")