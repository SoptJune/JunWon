package baekjun.graph.`플로이드-워셜`

private val br = System.`in`.bufferedReader()
private val bw = System.`out`.bufferedWriter()
private var n = 0
private var m = 0
private var graph = Array<IntArray>(101) { IntArray(101) { 1e9.toInt() } }
private var map = mutableMapOf<Pair<Int, Int>, List<Int>>()
fun main() {
    n = br.readLine().toInt(); m = br.readLine().toInt()
    repeat(m) {
        val (s, e, cost) = br.readLine().split(" ").map { it.toInt() }
        if (graph[s][e] > cost) {
            graph[s][e] = cost
            map += (s to e) to listOf<Int>(s, e)
        }
    }
    for (k in 1..100) {
        for (i in 1..100) {
            for (j in 1..100) {
                if (i == j) continue
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j]
                    map += (i to j) to (
                            map.getOrDefault(
                                i to k,
                                emptyList<Int>()
                            ) + map.getOrDefault(k to j, emptyList<Int>()).drop(1)
                            )
                }
            }
        }
    }
    for (i in 1..n) {
        val s = StringBuilder("")
        for (j in 1..n) {
            if (graph[i][j] >= 1e9.toInt()) s.append("0 ")
            else s.append("${graph[i][j]} ")
        }
        bw.write("${s.trim()}\n")
    }
    for (i in 1..n) {
        for (j in 1..n) {
            if (graph[i][j] >= 1e9.toInt()) bw.write("0\n")
            else {
                val route = map.getOrDefault(
                    i to j,
                    emptyList()
                )
                bw.write("${(listOf(route.size) + route).joinToString(" ")}\n")
            }
        }
    }
    bw.flush()
    bw.close()
}
