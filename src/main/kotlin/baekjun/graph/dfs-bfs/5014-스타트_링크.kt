package baekjun.graph.`dfs-bfs`

import java.util.*

private val br = System.`in`.bufferedReader()

// 스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층
//  (1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000) 
fun main() {
    val (H, s, target, U, D) = br.readLine().split(" ").map { it.toInt() }
    val vector = intArrayOf(U, if (D != 0) -D else 0)
    val q = LinkedList<Pair<Int, Int>>().apply { add(s to 0) } // 현재 층 , 이동 횟수
    val visited = BooleanArray(H + 1).apply { this[s] = true }
    while (q.isNotEmpty()) {
        val (cur, cost) = q.remove()
        if (cur == target) {
            println(cost)
            return
        }
        repeat(2) { i ->
            val next = cur + vector[i]
            if (next in 1..H && !visited[next]) {
                visited[next] = true
                q.add(next to cost + 1)
            }
        }
    }
    println("use the stairs")
}