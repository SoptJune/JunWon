import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

// N : 100_000 이하 정수, M : 1_000_000 이하 정수, C (1 .. 1000)
// 두 개의 마을로 나눠야 함 -> 집합 2개
// Disjoint - Set 문제네
// MST 만들고 가장 Cost가 큰 길을 없애면 된다.
// N-1 * 1000 <= 10^8 즉, Int로 충분
fun main() {
    val (N, M) = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    var ans = 0
    val costs = mutableListOf<Int>()
    val visited = BooleanArray(N + 1)
    val graph = Array(N + 1) { mutableListOf<Pair<Int, Int>>() }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    var u = 1
    visited[u] = true
    repeat(M) {
        val (s, e, c) = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
        graph[s].add(Pair(e, c))
        graph[e].add(Pair(s, c))
    }
    repeat(N - 1) {
        graph[u].forEach { (v, cost) ->
            if (!visited[v]) pq.add(Pair(v, cost))
        }
        while (pq.isNotEmpty()) {
            val (v, cost) = pq.remove()
            if (!visited[v]) {
                ans += cost
                costs.add(cost)
                visited[v] = true
                u = v
                break
            }
        }
    }
    bw.write("${ans - costs.max()}")
    bw.flush()
    baekjun.tree.`4803`.br.close()
    bw.close()
}