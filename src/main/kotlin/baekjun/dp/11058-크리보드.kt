
private val br = System.`in`.bufferedReader()
fun main() {
    val n = baekjun.tree.`4803`.br.readLine().toInt()
    val d = LongArray(100+1)
    d[1] = 1L
    d[2] = 2L
    d[3] = 3L
    for(i in 4 .. n) {
        var cnt = d[i-1] + 1
        for (j in i-3 downTo 1){
            cnt = maxOf(cnt, d[j]*(i-j -1))
        }
        d[i] = cnt
    }
    println(d[n])

}
