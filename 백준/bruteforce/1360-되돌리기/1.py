import sys
input = lambda : sys.stdin.readline().rstrip()
n = int(input())
stack = []
ans = ""
for _ in range(n):
    op, ch, time = input().split()
    if (op == "type"):
        time = int(time)
        if (stack):
            string = stack[-1][1] + ch
            stack.append((time, string))
        else:
            string = ch  # 헷갈리기 때문에 변수설정
            stack.append((time, ch))
    else:
        time, mTime = int(time), int(ch) # 헷갈리기 때문에 변수설정
        undoTime = time - mTime  # 80
        if(stack):
            curTime = stack[-1][0]
            if(curTime < undoTime):
                continue
            curIdx = len(stack)-1
            while(curIdx >= 0):
                curTime = stack[curIdx][0]
                if(undoTime <= curTime): # 15 17
                    curIdx -= 1
                else: # 13 > 12
                    break
            if(curIdx < 0):
                stack.append((time, ""))
            else:
                stack.append((time, stack[curIdx][1]))

if(stack): print(stack[-1][1])
else: print("")
