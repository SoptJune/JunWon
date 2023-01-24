
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
// n의 범위 3 ~ 200_000 
// O(N^2)으로 풀 수 없으니 브루트 포스는 아님
// 진입차수가 0 혹은 1일 때 간선들을 제거하는 방식으로 풀어야함  
// 약간, 위상정렬 문제 같긴한데 사이클이 존재할 수도 있고, 진입차수가 1일 때도 제거해야하니까 위상정렬은 아님
// stack 혹은 queue문제이다.

fun main() {
    val n = br.readLine().toInt()
    val indegree = IntArray(n + 1) // 진입 차수
    val graph = Array<IntArray>(n + 1) { IntArray(2) }
    val visited = BooleanArray(n + 1)
    repeat(n) { it ->
        val node = it + 1
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        graph[node][0] = a
        graph[node][1] = b
        indegree[a] += 1
        indegree[b] += 1
    }
    val stack = mutableListOf<Int>().apply {
        indegree.forEachIndexed { index, value ->
            // 진입 차수가 0 혹은 1인 노드만 stack에 넣기
            if (index != 0 && value in 0..1) add(index)
        }
    }
    while (stack.isNotEmpty()) {
        val curNode = stack.removeLast()
        if (visited[curNode]) continue
        visited[curNode] = true
        val a = graph[curNode][0]
        val b = graph[curNode][1]
        indegree[a] -= 1 // 진입 차수 -1
        indegree[b] -= 1 // 진입 차수 -1
        if (indegree[a] == 1) stack.add(a) // 진입차수가 1이면 node에 넣기
        if (indegree[b] == 1) stack.add(b) // 진입차수가 1이면 node에 넣기
    }
    val ansList =
        mutableListOf<Int>().apply {
            repeat(n + 1) { idx ->
                if (indegree[idx] == 2) add(idx)
            }
        }

    bw.write("${ansList.size}\n")
    if (ansList.size != 0) {
        bw.write(ansList.joinToString(" ") { it.toString() })
    }
    bw.flush()
    bw.close()
    br.close()
}