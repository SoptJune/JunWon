const val MOD = 9901
val br = System.`in`.bufferedReader()
fun main() {
    val n = br.readLine().toInt()
    val d = Array<IntArray>(n+1){IntArray(3)}.apply{ this[1][0] = 1;this[1][1] = 1;this[1][2] = 1;}

    for (i in 2 .. n) {
        d[i][0] = (d[i-1][0] + d[i-1][1] + d[i-1][2]) % MOD
        d[i][1] = (d[i-1][0] + d[i-1][1] )% MOD
        d[i][2] = (d[i-1][0] + d[i-1][2]) % MOD
    }
    println(d[n].sum() % MOD)
}