

private val br = System.`in`.bufferedReader()
private lateinit var graph: MutableList<MutableList<Int>>
private lateinit var visited: MutableList<MutableList<Int>>
fun main() {
    val (n, m) = baekjun.tree.`4803`.br.readLine().split(" ").map{ it.toInt() }

    
    graph = mutableListOf<MutableList<Int>>().apply {
        repeat(n) {
            val li = baekjun.tree.`4803`.br.readLine().split(" ").map{ it.toInt() }
            add(li.toMutableList())
        }
    }
    // 그래프 초기화 끝!
    var ans = 0
    while(!isAllRemoved() ) {
        // visitied 초기화
        visited =  MutableList<MutableList<Int>>(n){ MutableList<Int>(m){0}}
        // 빙하 녹임
        ans++
        removeIce(n,m)
        // println("==========$baekjun.getAns================")
        // graph.forEach {
        //     println(it)
        // }
        // 빙하 그룹
        var cnt = 0
        for( i in 0 until n) {
            for (j in 0 until m){
                if(graph[i][j] != 0 && visited[i][j] == 0) {
                    cnt += 1
                    dfs(n,m,i,j, cnt)
                }
            }
        }
        // println("==========visitied================")
        // visited.forEach { println(it)}
        if(cnt >= 2) return println(ans)
    }
    println(0)
}
private val dx = listOf(-1,0,1,0)
private val dy = listOf(0,1,0,-1)

private fun isAllRemoved() 
     = graph.fold(0){t, v -> t + v.sum()} == 0
private fun removeIce(n:Int, m: Int) {
    val graphCopy = graph.map { it.map{it}.toMutableList()}.toMutableList()
    // 빙하 녹이기
    for( i in 0 until n) {
        for (j in 0 until m){
            if(graph[i][j] != 0) {
                repeat(4) {
                    val nx = i + dx[it]
                    val ny = j + dy[it]
                    if(nx in 0 until n && ny in 0 until m){
                        if(graph[nx][ny] == 0){
                            if(graphCopy[i][j] > 0) graphCopy[i][j] -= 1
                            else graphCopy[i][j] = 0
                        }
                    }
                }
            }
        }
    }
    graph = graphCopy
}
// 이제 빙하 목록 찾기
private fun dfs(n: Int, m: Int, x: Int, y: Int, groupCnt: Int) {
    visited[x][y] = groupCnt
    repeat(4) {
        val nx = x + dx[it]
        val ny = y + dy[it]
        if(nx in 0 until n && ny in 0 until m){
            if(graph[nx][ny] != 0 && visited[nx][ny] == 0){
                dfs(n, m, nx, ny, groupCnt)
            }
        }
    }
}