import java.util.*
import kotlin.math.*

private val br = System.`in`.bufferedReader()
private var n = 0
private lateinit var visited: MutableMap<List<Int>, Boolean>
fun main() {
    visited = mutableMapOf<List<Int>, Boolean>()
    visited += listOf(1, 2) to true
    repeat(baekjun.tree.`4803`.br.readLine().toInt()) {
        baekjun.n = baekjun.tree.`4803`.br.readLine().toInt()
        var (s_x, s_y) = baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()}
        visited = mutableMapOf<List<Int>, Boolean>()
        repeat(baekjun.n) {
            visited += baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()} to false
        }
        val graph = visited.keys
        var (e_x, e_y) = baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()}

        var ans = "sad"

        val q = LinkedList<Pair<Int, Int>>().apply {
            add(s_x to s_y)
        }
        // println(graph)
        while(q.isNotEmpty()) {
            val (x, y) = q.remove()
            visited[listOf(x, y)] = true
            if(abs(x - e_x) + abs(y - e_y) <= 1000) {
                ans = "happy"
                break
            }

            graph.forEach {
                val (n_x, n_y) = it
                if(!visited[listOf(n_x, n_y)]!! && abs(x - n_x) + abs(y - n_y) <= 1000) {
                    visited += listOf(n_x, n_y) to true
                    q.add(n_x to n_y)
                }
            }
        }
        println(ans)
    }
}