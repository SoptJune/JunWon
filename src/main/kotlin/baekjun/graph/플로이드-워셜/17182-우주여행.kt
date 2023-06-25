package baekjun.graph.`플로이드-워셜`

private val br = System.`in`.bufferedReader()
private var n = 0;
var k = 0
val d = Array<IntArray>(10) { IntArray(10) }
const val MAX_D = 1001
var ans = MAX_D * 10
fun main() {
    val li = br.readLine().split(" ").map { it.toInt() }
    n = li[0]; k = li[1]
    repeat(n) { i ->
        d[i] = br.readLine()
            .split(" ")
            .mapIndexed { j, v ->
                if (i == j) MAX_D
                else v.toInt()
            }
            .toIntArray()
    }
    // 초기화 끝
    // 플로이드 워셜
    // d.toList().forEach{
    //     println(it.toList())
    // }
    repeat(n) { k ->
        repeat(n) { i ->
            repeat(n) { j ->
                d[i][j] = minOf(d[i][j], d[i][k] + d[k][j])
            }
        }
    }
    // d.toList().forEach{
    //     println(it.toList())
    // }
    // backTracking
    // k가 시작점
    back(k)
    println(ans)
}

private fun back(cur: Int, sum: Int = 0, depth: Int = 0, visited: Int = 0 or (1 shl cur)) {
    if (depth == n - 1) {
        ans = minOf(ans, sum)
        return
    }
    for (i in 0 until n) {
        if (visited and (1 shl i) == 0) {
            back(i, sum + d[cur][i], depth + 1, visited or (1 shl i))
        }
    }
}