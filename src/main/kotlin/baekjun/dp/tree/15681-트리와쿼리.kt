package baekjun.dp.tree

fun main() {
    // n : 정점 수, r : 루트 번호 , Q : 쿼리 수
    // (2 ≤ N ≤ 105, 1 ≤ R ≤ N, 1 ≤ Q ≤ 105)

    val (n, r, q) = readln().split(" ").map { it.toInt() }
    val graph = Array<MutableList<Int>>(n + 1) { mutableListOf() }
    val counts = IntArray(n + 1)
    // 1. graph 연결
    repeat(n - 1) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }
    // 2. graph -> tree로 변경
    fun dfs1(now: Int) {
        val cs = graph[now]

        cs.forEach { ch ->
            // 부모와의 간선 끊기
            graph[ch].remove(now)
            dfs1(ch)
        }
    }
    dfs1(r)
    // println(graph.toList())
    // 3. tree 순회하여 각 서브 노드 개수들 확인하기
    fun dfs2(now: Int): Int {

        var cnt = 1

        val cs = graph[now]
        if (cs.isEmpty()) {
            counts[now] = cnt
            return cnt
        }
        cs.forEach { ch ->
            cnt += dfs2(ch)
        }
        counts[now] = cnt
        return cnt
    }
    dfs2(r)
//    println(counts.toList())
    repeat(q) {
        println(counts[readln().toInt()])
    }
}