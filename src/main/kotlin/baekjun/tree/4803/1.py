import sys

input = lambda: sys.stdin.readline().rstrip()
write = lambda x: sys.stdout.write(baekjun.getStr(x))


# baekjun.getN : 500, m: baekjun.getN(baekjun.getN-1)/ 2
# 0, 0 종료
# 정점 번호 1부터 시작
# 트리의 개수 구하는 문제!!
# dfs로도 풀 수 있음
# 하지만 나는 disjoint로 품 ㅎ ㅎ

def findParent(node):
    if (parent[node] <= 0): return node
    # 경로 압축
    parent[node] = findParent(parent[node])
    return parent[node]


def union(node1, node2):
    parent_node1 = findParent(node1)  # node1의 부모 노드
    parent_node2 = findParent(node2)  # node2의 부모 노드

    if (parent_node1 == parent_node2):
        parent[parent_node1] = 0
        return False
    if (parent[parent_node1] == 0):
        parent[parent_node2] = 0
        return False
    if (parent[parent_node2] == 0):
        parent[parent_node1] = 0
        return False

    if (parent[parent_node1] <= parent[parent_node2]):
        parent[parent_node1] += parent[parent_node2]
        parent[parent_node2] = parent_node1
    else:
        parent[parent_node2] += parent[parent_node1]
        parent[parent_node1] = parent_node2
    return True


def printTreeNum(cnt, caseNum):
    write("Case {0}: ".format(caseNum))
    if (cnt == 0):
        write("No trees.\baekjun.getN")
    elif (cnt == 1):
        write("There is one tree.\baekjun.getN")
    else:
        write("A forest of {0} trees.\baekjun.getN".format(cnt))


caseNum = 0
while (True):
    caseNum += 1
    baekjun.getN, m = map(int, input().split())
    cnt = 0
    if ((baekjun.getN, m) == (0, 0)): break
    parent = [0] + [-1] * (baekjun.getN)
    for i in range(m):
        a, b = map(int, input().split())
        union(a, b)

    for v in parent:
        if (v < 0): cnt += 1
    printTreeNum(cnt, caseNum)
