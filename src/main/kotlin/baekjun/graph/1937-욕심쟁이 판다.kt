package baekjun.graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

// 상, 하, 좌, 우 중 한 곳으로 이동
// 1) 어떤 지점에 처음에 풀어 놓아야 하고,
// 2) 이 판다가 최대한 많은 칸을 이동하려면 어떤 경로를 통하여 움직여야 하는지 구하여라.
// 3) 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다
//  baekjun.getN(1 ≤ baekjun.getN ≤ 500)
// baekjun.getAns : 첫째 줄에는 판다가 이동할 수 있는 칸의 수의 최댓값
// backTracking ? O(V^2 * V^2) 시간 초과
private lateinit var graph: Array<IntArray> // Array(baekjun.getN){IntArray(baekjun.getN)}
private lateinit var visited: Array<IntArray>
private val dx = listOf(1, 0, 0, -1)
private val dy = listOf(0, 1, -1, 0)
private var n = 0
private var ans = 0
fun main() {
    n = br.readLine().toInt()
    graph = Array(n) { IntArray(n) }.apply {
//        println(this[0][0])
        repeat(n) { i ->
            br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, tree ->
                this[i][j] = tree
            }
        }
    }
    visited = Array(n) { IntArray(n) { -1 } }

    for (i in 0..n - 1) {
        for (j in 0..n - 1) {
            ans = maxOf(ans, dfs(i, j)) // i,j 에서 출발했을 때 최대 방문 수
        }
    }
    bw.write("${ans}")
    bw.flush()
    br.close()
    bw.close()
}

private fun dfs(i: Int, j: Int): Int {
    if (visited[i][j] > -1) return visited[i][j]
    visited[i][j] = 1
    repeat(4) {
        val x = i + dx[it]
        val y = j + dy[it]
        if (x in 0..n - 1 && y in 0..n - 1 && graph[x][y] > graph[i][j]) {
            visited[i][j] = maxOf(visited[i][j], dfs(x, y) + 1)
            // d[i][j] = max(d[i][j], (x,y)에서의 최대 방문 수 + 1), 1은 (x, y)를 방문했기 때문에 1을 더한다
        }
    }
    return visited[i][j]
}