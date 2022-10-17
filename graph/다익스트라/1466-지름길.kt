package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
private lateinit var pq: PriorityQueue<List<Int>>
private var distances = IntArray(10_001) {
    Int.MAX_VALUE
}.apply { set(0, 0) }
private lateinit var graph: List<List<List<Int>>>
private var n = 0
private var TARGET_NODE = 0
fun main() {
    br.readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        TARGET_NODE = it[1]
    }

    graph = mutableListOf<MutableList<List<Int>>>().apply {
        repeat(TARGET_NODE + 1) {
            add(mutableListOf())
        }
    }.also { graph ->
        repeat(TARGET_NODE) {
            graph[it].add(listOf(it + 1, 1))
        }
    }.also { graph ->
        repeat(n) {
            val (s, e, cost) = br.readLine().split(" ").map { it.toInt() }
            if (s >= TARGET_NODE || e > TARGET_NODE) return@repeat
            graph[s].add(listOf(e, cost))
        }
    }
    pq = PriorityQueue<List<Int>> { a, b ->
        a[0].compareTo(b[0])
    }.apply { offer(listOf(0, 0)) }

    // 다익스트라
    while (pq.isNotEmpty()) {
        val (cost, curNode) = pq.poll()

        if (distances[curNode] < cost) continue

        graph[curNode].forEach { nextNodeInfo ->
            val nextNode = nextNodeInfo[0]
            val nextCost = distances[curNode] + nextNodeInfo[1]
            if (nextCost < distances[nextNode]) {
                distances[nextNode] = nextCost
                pq.add(listOf(nextCost, nextNode))
            }
        }
    }
    bw.write("${distances[TARGET_NODE]}")
    bw.flush()
    bw.close()
    br.close()
}