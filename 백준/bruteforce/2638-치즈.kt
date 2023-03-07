var n =0; var m = 0;
val br = System.`in`.bufferedReader()
private val graph = MutableList<MutableList<Int>>(100){MutableList<Int>(100){0}}
private var area = MutableList<MutableList<Int>>(100){MutableList<Int>(100){-1}}
fun main() {
    val (_n, _m) = br.readLine().split(" ").map{it.toInt()}
    n = _n; m = _m
    repeat(n) { i ->
        graph[i] = br.readLine().split(" ").map{it.toInt()}.toMutableList()
    }
    var ans = 0
    while(!isAllZero()) {
        ans++
        d0()
        d1()
        d2()        
        // graph.forEach{
        //     println(it.joinToString(" "))
        // }
    }
    println(ans)
}

private val dx = intArrayOf(1,0,0,-1)
private val dy = intArrayOf(0,1,-1,0)

// 0. 구역 정하기 
fun d0() {
    area = MutableList<MutableList<Int>>(100){MutableList<Int>(100){-1}}
    repeat(n) { x ->
        repeat(m) { y ->
            if(x == 0 && y == 0) dfs(x,y,0)
            else if(area[x][y] == -1 && graph[x][y] == 0) dfs(x,y, 1)
        }
    }
}
fun dfs(x: Int, y: Int, num: Int) {
    area[x][y] = num
    repeat(4) { i ->
        val nx = x + dx[i]
        val ny = y + dy[i]
        if(nx in 0 until n && ny in 0 until m && graph[nx][ny] == 0 && area[nx][ny] == -1) {
            dfs(nx,ny,num)
        }
    }
}
// 1. area가 0인 2면 이상 닿은 블럭은 2로 바꿈
fun d1() {
    repeat(n) { x ->
        repeat(m) { y ->
            if(graph[x][y] == 1){
                var cnt = 0
                repeat(4) { i ->
                val nx = x + dx[i]
                val ny = y + dy[i]
                if(nx in 0 until n && ny in 0 until m && graph[nx][ny] == 0 && area[nx][ny] == 0) cnt++
                }
                if(cnt >= 2) graph[x][y] = 2
            }
        }
    }
    // graph.forEach{
    //     println(it.joinToString(" "))
    // }
}
// 2. 2로 바뀐 치즈들 0으로 바꾸기
fun d2() {
     repeat(n) { x ->
        repeat(m) { y ->
           if(graph[x][y] == 2) graph[x][y] = 0
        }
    }
}
// 3. 전체가 전부 0으로 바뀌었는지 체크
fun isAllZero() = graph.all{ it.sum() == 0}
// 3. 1, 2번 반복