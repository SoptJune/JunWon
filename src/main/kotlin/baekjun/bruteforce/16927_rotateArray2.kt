package baekjun.bruteforce

fun main() {
    // 2 ≤ N, M ≤ 300, R <= 10^9
    // element <= 10^8
    val (n, m, r) = readln().split(" ").map { it.toInt() }
    val graph = Array<IntArray>(n) { IntArray(m) }
    for (i in 0 until n) {
        graph[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    rotate(graph, n - 1, m - 1, 0, 0, r)
    graph.forEach {
        println(it.joinToString(" "))
    }
}

private fun rotate(
    arr: Array<IntArray>,
    xL: Int,
    yL: Int,
    x: Int,
    y: Int,
    _r: Int
) {
    if (xL <= 0 || yL <= 0) return
    val r = _r % (2 * (xL + yL))
    repeat(r) {
        val temp = arr[x][y] // 시작점 임시 저장
        // 왼쪽으로 shift (x, y +yL) -> (x, y)
        repeat(yL) { i ->
            arr[x][y + i] = arr[x][y + i + 1]
        }
        // 위로 shift (x+xL, y + yL) -> (x, y+yL)
        repeat(xL) { i ->
            arr[x + i][y + yL] = arr[x + i + 1][y + yL]
        }
        // 오른쪽 (x+xL, y ) -> (x+xL, y+yL )
        repeat(yL) { i ->
            arr[x + xL][y + yL - i] = arr[x + xL][y + yL - i - 1]
        }
        // 아래 (x, y) -> (x + x+xL, y)
        repeat(xL) { i ->
            arr[x + xL - i][y] = arr[x + xL - i - 1][y]
        }
        arr[x + 1][y] = temp
    }

    rotate(arr, xL - 2, yL - 2, x + 1, y + 1, _r)
}