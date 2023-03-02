private val br = System.`in`.bufferedReader()
private var cnt = 0
private val dx = listOf(-1,0,1,0)
private val dy = listOf(0,1,0,-1)
private lateinit var graph: Array<IntArray>
fun main() {
    println(Shape.E.name)
    val (n, m) = br.readLine().split(" ").map{it.toInt()}
    val (x, y, d) = br.readLine().split(" ").map{it.toInt()}
    graph = Array<IntArray>(n){IntArray(m)}.apply {
        repeat(n) {
            this[it] = br.readLine().split(" ").map{it.toInt()}.toIntArray()
        }
    }
    
    dfs(n,m, x,y, d)
    println(cnt)
}

private fun dfs(n:Int, m:Int, x:Int, y:Int, d:Int) {
    
    // 현재 칸 청소
    if(graph[x][y] == 0) {
        graph[x][y] = -1
        cnt++
    }
    
    // 이동 및 회전 가능 체크
    var isRotate = false
    repeat(4) { i ->
        val nx = x + dx[i]
        val ny = y + dy[i]
        if(nx in 0 .. n-1 && ny in 0 .. m -1 && graph[nx][ny] == 0) { 
            isRotate = true // 회전 가능
            // 이동
            if(i == d) {
                return dfs(n, m, nx, ny, d)
            }
        }
    }
    // 반시계 회전
    if(isRotate) return dfs(n, m, x, y, (d + 4 - 1) % 4)     
    
    // 후진
    val backD = (d + 2) % 4
    val nx = x + dx[backD] // d 반대방향
    val ny = y + dy[backD]
    // 벽이 없고, 범위내에 있으면 후진한다
    if(nx in 0 .. n-1 && ny in 0 .. m -1  && graph[nx][ny] != 1) {
        return dfs(n, m, nx, ny, backD)
    }

    // 운행 종료
    return
}