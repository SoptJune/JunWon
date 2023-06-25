package baekjun.graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.max
import java.util.*

// N은 2 이상 100 이하의 정수
// 높이는 1이상 100 이하의 정수이다.
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

private var n = 0
private var ans = 1
private var graph = mutableListOf<List<Int>>()
private var visited = mutableListOf<IntArray>()
private var height = 0
private val dx = listOf(1, 0, 0, -1)
private val dy = listOf(0, 1, -1, 0)
fun main() {
    n = br.readLine().toInt()
    repeat(n) {
        graph.add(br.readLine().split(" ").map { it.toInt() })
    }
    repeat(100) { it ->
        height = it + 1
        repeat(n) {
            visited.add(
                IntArray(n) {
                    -1
                }
            )
        }
        // 0 이면 height보다 작은 녀석들 싹다 0으로 바꾸기
        // heiht보다 크면 주변 녀석들 싹다 result로 바꾸기
        var result = 1
        repeat(n) { i ->
            repeat(n) { j ->
                if (visited[i][j] == -1) {
                    if (graph[i][j] <= height) {
                        visited[i][j] = 0
                        bfs(i, j)
                    } else {
                        visited[i][j] = result
                        bfs(i, j, result)
                        result++
                    }
                }
            }
        }
        ans = max(ans, result - 1)
        visited.clear()
    }
    bw.write("$ans\n")
    bw.flush()
    br.close()
    bw.close()
}

private fun bfs(i: Int, j: Int) {
    val queue = LinkedList<List<Int>>().apply {
        push(listOf(i, j))
    }
    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()!!
        repeat(4) {
            val n_x = x + dx[it]
            val n_y = y + dy[it]
            if (n_x in (0..n - 1) && n_y in (0..n - 1)) {
                if (visited[n_x][n_y] == -1 && graph[n_x][n_y] <= height) {
                    visited[n_x][n_y] = 0
                    queue.push(listOf(n_x, n_y))
                }
            }
        }
    }
}

private fun bfs(i: Int, j: Int, result: Int) {
    val queue = LinkedList<List<Int>>().apply {
        add(listOf(i, j))
    }
    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()!!
        repeat(4) {
            val n_x = x + dx[it]
            val n_y = y + dy[it]
            if (n_x in (0..n - 1) && n_y in (0..n - 1)) {
                if (visited[n_x][n_y] == -1 && graph[n_x][n_y] > height) {
                    visited[n_x][n_y] = result
                    queue.push(listOf(n_x, n_y))
                }
            }
        }
    }
}
