package init.mst

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

// 네트워크 연결 문제 (백준 - 1922)
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    var ans = 0
    val visited = BooleanArray(1001)
    val graph = List<MutableList<Pair<Int, Int>>>(N + 1) { mutableListOf() }
    // 0. 인접 리스트 방식 graph 생성
    repeat(M) {
        val (a, b, cost) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, cost))
        graph[b].add(Pair(a, cost))
    }
    var u = 1 // 1. 시작 정점 세팅
    visited[u] = true
    // 2. 시작 정점을 pq에 넣는다.
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }).apply { add(Pair(u, 0)) }

    // 3. N - 1번 반복
    repeat(N - 1) {
        // 4. 현재 정점과 연결된 정점들을 pq에 넣는다.
        graph[u].forEach {
            pq.add(it)
        }
        // 5. pq에서 가중치가 가장 작은 간선을 뽑는다.
        while (pq.isNotEmpty()) {
            val (v, cost) = pq.remove()
            // 6. 이미 방문한 정점이면 넘어간다.
            if (!visited[v]) {
                // 7. 방문하지 않은 정점이면 정답에 가중치를 더하고 방문 처리를 한다.
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