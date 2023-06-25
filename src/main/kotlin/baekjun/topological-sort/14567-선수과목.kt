package baekjun.`topological-sort`

import java.io.*
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val indegree = IntArray(n + 1)
    val ansArr = IntArray(n + 1)
    val graph = Array<MutableList<Int>>(n + 1) { mutableListOf<Int>() }.apply {
        repeat(m) {
            // a가 b보다 앞에 있어야 함
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            this[a].add(b)
            indegree[b] += 1
        }
    }
    val q = LinkedList<Pair<Int,Int>>().apply {
        repeat(n) {
            val idx = it + 1
            if (indegree[idx] == 0) add(Pair(1,idx))
        }
    }

    while (q.isNotEmpty()) {
        val (cnt, node) = q.pop()
        ansArr[node] = cnt
        graph[node].forEach {
            indegree[it] -= 1
            if (indegree[it] == 0) q.add(Pair(cnt+1, it))
        }
    }
    bw.write(ansArr.drop(1).joinToString(" ") { it.toString() })
    bw.flush()
    bw.close()
    br.close()
}

