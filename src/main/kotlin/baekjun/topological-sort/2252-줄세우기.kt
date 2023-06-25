package baekjun.`topological-sort`
import java.io.*
import java.util.*
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val indegree = IntArray(n + 1)
    val ansList = mutableListOf<Int>()
    val graph = Array<MutableList<Int>>(n + 1) { mutableListOf<Int>() }.apply {
        repeat(m) {
            // a가 b보다 앞에 있어야 함
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            this[a].add(b)
            indegree[b] += 1
        }
    }
    val q = LinkedList<Int>().apply {
        repeat(n) {
            val idx = it + 1
            if (indegree[idx] == 0) add(idx)
        }
    }
    while (q.isNotEmpty()) {
        val node = q.pop()
        ansList.add(node)
        graph[node].forEach {
            indegree[it] -= 1
            if (indegree[it] == 0) q.add(it)
        }
    }
    bw.write(ansList.joinToString(" ") { it.toString() })
    bw.flush()
    bw.close()
    br.close()
}
