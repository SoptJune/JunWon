
private val br = System.`in`.bufferedReader()
private var n = 0
private var m = 0
private lateinit var graph: MutableList<MutableList<Int>>
private lateinit var visited: BooleanArray
private var ans = -1
fun main() {
    n = br.readLine().toInt()
    val (a, b) = br.readLine().split(" ").map{ it.toInt()}
    m = br.readLine().toInt()
    graph = mutableListOf<MutableList<Int>>().apply{
        repeat(n+1) {
            add(mutableListOf<Int>())
        }
    }
    visited = BooleanArray(n + 1)
    repeat(m) {
        val (s, e) = br.readLine().split(" ").map{ it.toInt()}
        graph[s].add(e)
        graph[e].add(s)
    }
    dfs(a, b, 0)
    println(ans)
}

private fun dfs(x: Int, y: Int,  depth: Int) {
    visited[x] = true
    if(x == y) {
        ans = depth
        return
    }

    graph[x].forEach { p ->
        if(!visited[p]) {
            dfs(p, y, depth+1)
        }
    }
}