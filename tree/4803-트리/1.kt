import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
lateinit var graph : MutableList<MutableList<Int>>
lateinit var visited: BooleanArray
var case = 0
fun main() {
    while (true) {
        var ans = 0
        // 초기화
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        visited = BooleanArray(n+1).apply { set(0, true) } // 노드의 방문기록 
        graph = mutableListOf<MutableList<Int>>().apply { // 인접리스트 방식으로 그래프의 연결방식을 표현
            repeat(n+1) {
                add(mutableListOf())
            }
        }
        case++
        if (n == 0 && m == 0) {
            bw.flush()
            bw.close()
            br.close()
            return
        }
        repeat(m) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            graph[a].add(b)
            graph[b].add(a)
        }
        repeat(n + 1) lab@{ idx ->
            if (idx == 0) return@lab

            if (!visited[idx] && !bfs(idx)) {
                ans++
            }
        }
        when (ans) {
            0 -> {
                bw.write("Case $case: No trees.\n")
            }
            1 -> {
                bw.write("Case $case: There is one tree.\n")
            }
            else -> {
                bw.write("Case $case: A forest of $ans trees.\n")
            }
        }
    }
}

private fun bfs(node: Int): Boolean {
    var isCycle = false
    val q = LinkedList<Int>().apply { add(node) }

    while (q.isNotEmpty()) {
        val curNode = q.poll()
        if(visited[curNode]){
            isCycle = true
        }
        visited[curNode] = true
        for (nextNode in graph[curNode]) {
            if (!visited[nextNode]) {
                q.add(nextNode)
            }
        }
    }
    return isCycle
}

