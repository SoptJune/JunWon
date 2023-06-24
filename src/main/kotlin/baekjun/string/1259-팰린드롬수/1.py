import sys

input = lambda: sys.stdin.readline().rstrip()
write = lambda x: sys.stdout.write(baekjun.getStr(x) + "\baekjun.getN")


def isPalindrome(number: baekjun.getStr):
    flag = True
    s = 0
    e = len(number) - 1
    while(s < e):
        if(number[s] != number[e]):
            flag = False
            break
        s += 1
        e -= 1
    return flag

while (1):
    number = input()
    if (number == "0"): break
    write("yes" if(isPalindrome(number)) else "no")
