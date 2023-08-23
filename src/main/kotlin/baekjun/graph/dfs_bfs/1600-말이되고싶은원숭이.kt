package baekjun.graph.dfs_bfs

import java.util.*

fun main() {
    val R = readln().toInt()
    val (M, N) = readln().split(" ").map { it.toInt() }
    val dx = intArrayOf(1, 0, 0, -1)
    val dy = intArrayOf(0, 1, -1, 0)

    val dx2 = intArrayOf(1, 1, -1, -1, 2, 2, -2, -2)
    val dy2 = intArrayOf(-2, 2, 2, -2, 1, -1, 1, -1)
    val graph = Array<IntArray>(N) {
        intArrayOf()
    }.apply {
        repeat(N) { i ->
            this[i] = readln().split(" ").map { it.toInt() }.map {
                if (it == 0) it - 1
                else it
            }.toIntArray()
        }
        this[0][0] = 0
    }.let { g ->
        Array(R + 1) { g.map { it.copyOf() } }
    }

    data class Horse(val x: Int = 0, val y: Int = 0, val rest: Int)

    val q = LinkedList<Horse>().apply { add(Horse(rest = R)) }

//    graph.forEach { println(it.toList()) }
    while (q.isNotEmpty()) {
        val (x, y, r) = q.poll()

        if (x == N - 1 && y == M - 1) {
            println(graph[r][x][y])
            return
        }
        var nx = 0;
        var ny = 0
        // 순회
        repeat(4) {
            nx = x + dx[it]
            ny = y + dy[it]

            if (nx in 0 until N && ny in 0 until M && graph[r][nx][ny] == -1) {
                graph[r][nx][ny] = graph[r][x][y] + 1
                q.add(Horse(nx, ny, r))
            }
        }
        if (r > 0) {
            repeat(8) {
                nx = x + dx2[it]
                ny = y + dy2[it]
                if (nx in 0 until N && ny in 0 until M && graph[r - 1][nx][ny] == -1) {
                    graph[r - 1][nx][ny] = graph[r][x][y] + 1
                    q.add(Horse(nx, ny, r - 1))
                }
            }
        }
    }
    println(-1)
}