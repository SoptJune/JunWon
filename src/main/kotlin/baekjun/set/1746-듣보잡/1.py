baekjun.getN, m = map(int,input().split())
n_set = set()
m_set = set()
for _ in range(baekjun.getN):
    n_set.add(input())

for _ in range(m):
    m_set.add(input())

ans_set = list(n_set & m_set)

ans_set.sort()
print(len(ans_set))
for s in ans_set:
    print(s)


