package baekjun.graph.dfs_bfs

import java.util.*
import kotlin.system.exitProcess

fun main() {
    // s, t  (1.. 1e9)
    val (s, t) = readln().split(" ").map { it.toInt() }
    val ansList = mutableListOf<String>()
    var ansD = Int.MAX_VALUE
    val ops = listOf("*", "+", "/")
    val visited = BooleanArray(1e9.toInt() + 1)
    // 1. t가 자기 자신이면 0 반환
    if (s == t) run { println(0); exitProcess(0) }
    data class Op(val num: Int, val res: String, val depth: Int)
    // bfs 시작
    val q = LinkedList<Op>().apply { add(Op(s, "", 0)) }

    while (q.isNotEmpty()) {
        val (now, res, d) = q.poll()
        if (ansD < d) break
        if (now == t) {
            ansD = d
            ansList.add(res)
        }
        // 경로 이동
        repeat(3) {
            var next: Long = now.toLong()
            val op = ops[it]
            when (op) {
                "+" -> next += now
                "*" -> next *= now // 범위 체크
                "/" -> next /= now
            }
            if (next in 1..1e9.toInt() && visited[next.toInt()].not()) {
                val n = next.toInt()
                visited[n] = true
                q.add(Op(n, res + op, d + 1))
            }
        }
    }

    println(ansList.sorted().firstOrNull() ?: -1)
}