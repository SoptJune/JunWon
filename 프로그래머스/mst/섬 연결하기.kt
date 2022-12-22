package algorithm

import java.util.*

fun main() {
    solution(
        4,
        arrayOf(intArrayOf(0, 1, 1), intArrayOf(0, 2, 2), intArrayOf(1, 2, 5), intArrayOf(1, 3, 1), intArrayOf(2, 3, 8))
    )
}
// 최단경로 찾는 문제인가 싶었지만 graph 문제였네용
// MST 문제
val parent = IntArray(100) { -1 }
val pq = PriorityQueue<IntArray>(compareBy { it[2] }) // cost를 comparator로 지정
fun solution(n: Int, routes: Array<IntArray>): Int {
    var answer = 0
    routes.forEach { route ->
        pq.add(route)
    }
    repeat(pq.size) {
        val route = pq.poll()
        if (union(route[0], route[1])) answer += route[2]
    }
    println(answer)
    return answer
}

fun findParent(x: Int): Int {
    if (parent[x] < 0) return x
    parent[x] = findParent(parent[x])
    return parent[x]
}

fun union(c1: Int, c2: Int): Boolean {
    val r1 = findParent(c1)
    val r2 = findParent(c2)

    if (r1 == r2) return false

    if (parent[r1] <= parent[r2]) {
        parent[r1] += parent[r2]
        parent[r2] = r1
    } else {
        parent[r2] += parent[r1]
        parent[r1] = r2
    }
    return true
}