
const val MOD = 1_000_000_007
val br = System.`in`.bufferedReader()
val d = LongArray(5001).apply{this[0] = 1; this[1]= 1}
fun main() {
    // 5000
    catalan(2500)
    // 카탈란 수 
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        if(n % 2 == 1) println(0)
        else println(d[n / 2])
    }
}

fun catalan(n: Int): Long
 {
    if(d[n] != 0L) return d[n]
    
    var cnt = 0L
    repeat(n) {
        cnt = (cnt + ((catalan(it) % MOD) * (catalan(n - it - 1) % MOD)) % MOD) % MOD
    }
    d[n] = cnt % MOD
    return d[n]
}