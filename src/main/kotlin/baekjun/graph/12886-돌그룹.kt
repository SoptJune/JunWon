package baekjun.graph
private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
// 강호는 모든 그룹에 있는 돌의 개수를 같게 만들려고 한다.
// 크기가 같지 않은 두 그룹을 고른다. (x < y)
// x -> x+x , y -> y - x
// 강호가 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력
// (1 ≤ A, B, C ≤ 500)

// 중복 처리 가능.. dfs/bfs + 메모이제이션 ㄱ ㄱ
// a+b+c % 3 > 0 => baekjun.getAns 0

// 10
private var pivot = 0
private val visited = Array(1499){IntArray(1499)}
fun main() {
    val (a, b, c) =br.readLine().split(" ").map{it.toInt()}
    val total = a + b + c
    pivot = total / 3
    if(total % 3 > 0){
        bw.write("0")
        bw.flush()
        bw.close()
        br.close()
        return
    }
    dfs(a, b, c)
    bw.write("${visited[pivot][pivot]}")
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(a: Int, b:Int, c:Int) {
    if(!(a in 1 .. 1498 && b in 1 .. 1498 && c in 1 .. 1498)) return
    if(visited[a][b] == 1) return
    visited[a][b] = 1
    visited[b][a] = 1
    var x = 0
    var y = 0
    
    x = minOf(a, b) ; y = maxOf(a, b)
    dfs(x + x, y - x, c)
    x = minOf(a, c) ; y = maxOf(a, c)
    dfs(x + x, y - x, b)
    x = minOf(b, c) ; y = maxOf(b, c)
    dfs(x + x, y - x, a)
}
