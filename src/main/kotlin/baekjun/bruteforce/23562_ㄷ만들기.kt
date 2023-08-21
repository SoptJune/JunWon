package baekjun.bruteforce

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val (bC, wC) = readln().split(" ").map { it.toInt() }
    val _graph = Array(n) { readln().toCharArray() }
    val B = '#'
    val W = '.'
    var ans = Int.MAX_VALUE
    val L = run {
        val nk = n / 3
        val mk = m / 3
        minOf(nk, mk)
    }

    repeat(L) { _l ->
        val l = _l + 1

        fun execute(x: Int, y: Int): Int { // O(nm)
            val graph = _graph.map { it.clone() }.toTypedArray()
            var cnt = 0
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (i in x until x + l && j in y until y + 3 * l) {
                        if (graph[i][j] == W) {
                            graph[i][j] = B
                            cnt += bC
                        }
                        continue
                    }
                    if (i in x + l until x + 2 * l && j in y until y + l) {
                        if (graph[i][j] == W) {
                            graph[i][j] = B
                            cnt += bC
                        }
                        continue
                    }
                    if (i in x + l until x + 2 * l && j in y + l until y + 3 * l) {
                        if (graph[i][j] == B) {
                            graph[i][j] = W
                            cnt += wC
                        }
                        continue
                    }
                    if (i in x + 2 * l until x + 3 * l && j in y until y + 3 * l) {
                        if (graph[i][j] == W) {
                            graph[i][j] = B
                            cnt += bC
                        }
                        continue
                    }
                    if (graph[i][j] == B) {
                        graph[i][j] = W
                        cnt += wC
                    }
                }
            }
            return cnt
        }
        for (i in 0..n - 3 * l) {
            for (j in 0..m - 3 * l) {
                ans = minOf(ans, execute(i, j))
            }
        }
    }
    println(ans)
}