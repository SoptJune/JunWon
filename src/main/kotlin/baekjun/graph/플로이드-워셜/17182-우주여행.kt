
val br = System.`in`.bufferedReader()
var n = 0; var k = 0
val d = Array<IntArray>(10){IntArray(10)}
const val MAX_D = 1001
var ans = MAX_D * 10
fun main() {
    val li = baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()}
    baekjun.n = li[0]; k = li[1]
    repeat(baekjun.n) { i ->
         d[i] = baekjun.tree.`4803`.br.readLine()
            .split(" ")
            .mapIndexed{ j, v ->
                 if(i == j) MAX_D
                 else v.toInt()}
            .toIntArray()   
    }   
    // 초기화 끝
    // 플로이드 워셜
    // d.toList().forEach{
    //     println(it.toList())
    // }
    repeat(baekjun.n){ k ->
            repeat(baekjun.n){ i ->
                    repeat(baekjun.n){ j ->
                              d[i][j] = minOf(d[i][j], d[i][k] + d[k][j])
                    }
            }
    }
    // d.toList().forEach{
    //     println(it.toList())
    // }
    // backTracking
    // k가 시작점
    back(k)
    println(baekjun.ans)
}
fun back(cur: Int, sum:Int = 0, depth: Int = 0, visited: Int = 0 or (1 shl cur)) {
    if(depth == baekjun.n -1) {
        baekjun.ans = minOf(baekjun.ans, sum)
        return
    }
    for(i in 0 until baekjun.n) {
        if(visited and (1 shl i) == 0) {
            back(i, sum + d[cur][i], depth+1, visited or (1 shl i))
        }
    }
}