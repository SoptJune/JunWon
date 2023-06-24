import java.util.*

private val br = System.`in`.bufferedReader()
private var n = 0
private var m = 0
private lateinit var graph: MutableList<MutableList<Int>>
private lateinit var visited: BooleanArray
private var ans = -1
fun main() {
    baekjun.n = baekjun.tree.`4803`.br.readLine().toInt()
    val (a, b) = baekjun.tree.`4803`.br.readLine().split(" ").map{ it.toInt()}
    m = baekjun.tree.`4803`.br.readLine().toInt()
    graph = mutableListOf<MutableList<Int>>().apply{
        repeat(baekjun.n +1) {
            add(mutableListOf<Int>())
        }
    }
    visited = BooleanArray(baekjun.n + 1)
    repeat(m) {
        val (s, e) = baekjun.tree.`4803`.br.readLine().split(" ").map{ it.toInt()}
        graph[s].add(e)
        graph[e].add(s)
    }
    // dfs(a, b, 0)
    baekjun.ans = bfs(a,b)
    println(baekjun.ans)
}

private fun dfs(x: Int, y: Int,  depth: Int) {
    visited[x] = true
    if(x == y) {
        baekjun.ans = depth
        return
    }

    graph[x].forEach { p ->
        if(!visited[p]) {
            dfs(p, y, depth+1)
        }
    }
}

fun bfs(x:Int, y: Int): Int {
    val q = LinkedList<Pair<Int, Int>>().apply{
        add(x to 0)
    }
    while(q.isNotEmpty()) {
        val (now, depth) = q.remove()
        visited[now] = true
        if(now == y) {
            return depth
        }
        graph[now].forEach {
            if(!visited[it]) q.add(it to depth+1)
        }
    }
    return -1
}