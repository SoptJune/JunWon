package com.sopt.peekabookaos.presentation.barcodeScanner

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

// mst 문제
// n : 1 .. 1000, m : 1.. 100_000 , c : 10_000
// 모든 비용의 합은 1e9를 넘지 않으니 Int로 충분~
// 무방향 그래프 만들면 끝!

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
val parents = IntArray(1001) { -1 }

// 프림
fun main2() {
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    var ans = 0
    val visited = BooleanArray(1001)
    val graph = List<MutableList<Pair<Int, Int>>>(N + 1) { mutableListOf() }
    repeat(M) {
        val (a, b, cost) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, cost))
        graph[b].add(Pair(a, cost))
    }
    var u = 1
    visited[u] = true
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }).apply { add(Pair(u, 0)) }

    repeat(N - 1) {
        graph[u].forEach {
            pq.add(it)
        }
        while (pq.isNotEmpty()) {
            val (v, cost) = pq.remove()
            if (!visited[v]) {
                ans += cost
                visited[v] = true
                u = v
                break
            }
        }
    }

    bw.write("$ans")
    bw.flush()
    bw.close()
    br.close()
}

// 크루스칼
fun main() {
    br.readLine().toInt()
    val M = br.readLine().toInt()
    var ans = 0
    val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third }).apply {
        repeat(M) {
            val (a, b, cost) = br.readLine().split(" ").map { it.toInt() }
            add(Triple(a, b, cost))
        }
    }
    while (pq.isNotEmpty()) {
        val (u, v, cost) = pq.remove()
        if (union(u, v)) {
            ans += cost
        }
    }

    bw.write("$ans")
    bw.flush()
    bw.close()
    br.close()
}

fun findParent(curNode: Int): Int {
    if (parents[curNode] <= -1) return curNode

    parents[curNode] = findParent(parents[curNode])
    return parents[curNode]
}

fun union(x: Int, y: Int): Boolean {
    val parentX = findParent(x)
    val parentY = findParent(y)

    if (parentX == parentY) return false

    if (parents[parentX] >= parents[parentY]) {
        parents[parentY] += parents[parentX]
        parents[parentX] = parentY
    } else {
        parents[parentX] += parents[parentY]
        parents[parentY] = parentX
    }
    return true
}
