package programmers.`disjoint-set`
fun main() {
    println(solution(3, arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1))))
}

// n은 1 이상 200 이하인 자연수
// 컴퓨터는 0부터 baekjun.getN-1인 정수로
private lateinit var parents: MutableList<Int>
private fun solution(n: Int, computers: Array<IntArray>): Int {
    parents = MutableList(n) { -1 }
    repeat(n) { i ->
        repeat(n) { j ->
            if (computers[i][j] == 1) union(i, j)
        }
    }
    var answer = 0
    // root node에 해당하는 녀석들의 수만 answer에 더해준다 
    parents.forEach { if (it < 0) answer++ }
    return answer
}

private fun union(x: Int, y: Int) {
    val parentOfx = findParentOf(x)
    val parentOfy = findParentOf(y)
    if (parentOfx == parentOfy) return
    if (parents[parentOfx] <= parents[parentOfy]) {
        parents[parentOfx] += parents[parentOfy]
        parents[parentOfy] = parentOfx
    } else {
        parents[parentOfy] += parents[parentOfx]
        parents[parentOfx] = parentOfy
    }
}

private fun findParentOf(node: Int): Int {
    if (parents[node] < 0) return node
    parents[node] = findParentOf(parents[node])
    return parents[node]
}
