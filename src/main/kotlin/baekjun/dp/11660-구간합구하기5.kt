val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

// x1,y1 ~ x2,y2 합 구하는 프로그램 작성하기
//  (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000)
// 0 ~ x,y 까지 합 구하는 점화식 d[x][y] = a[x][y] + d[x - 1][y] + d[x][y-1] - d[x-1][y-1]
// x1y1 ~ x2y2 까지 합 구하는 방정식 b[x1y1 ~ x2y2] = a[x2][y2] - a[x1 - 1][y2] - a[x2][y1-1] + a[x1 - 1][y - 1]
//  표에 채워져 있는 수는 1,000보다 작거나 같은 자연수 -> 모든 누적합 1e9 이하 Int충분
private lateinit var graph: List<List<Int>>
private lateinit var d: Array<IntArray>
fun main() {
    val (n, m) = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    graph = mutableListOf<List<Int>>().apply {
        add(listOf())
        repeat(n) {
            add(listOf(0) + baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() })
        }
        d = Array(n + 1) { IntArray(n + 1) }
    }
    // 초기화
    for (i in 1..n) {
        for (j in 1..n) {
            d[i][j] = graph[i][j] + d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1]
        }
    }

    repeat(m) { _ ->
        val (x1, y1, x2, y2) = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
        bw.write("${providePremixSumRangeOf(x1, y1, x2, y2)}\n")
        bw.flush()
    }
    bw.close()
    baekjun.tree.`4803`.br.close()
}

fun providePremixSumRangeOf(x1: Int, y1: Int, x2: Int, y2: Int) =
    d[x2][y2] - d[x1 - 1][y2] - d[x2][y1 - 1] + d[x1 - 1][y1 - 1]
