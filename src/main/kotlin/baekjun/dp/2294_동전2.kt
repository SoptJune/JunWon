package baekjun.dp

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
fun main() {
    val maxValue = 100_001
    val limit = 10_000
    // n <= 100, k <= 10,000
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val values = IntArray(n).apply {
        repeat(n) {
            val v = br.readLine().toInt()
            if(v > limit) return@repeat
            this[it] = v
        }
    }
    val d = IntArray(10_001) { maxValue }.apply {
        values.forEach { v ->
            this[v] = 1
        }
    }
    repeat(k + 1) { i ->
        values.forEach { v ->
            val diff = i - v
            if (diff >= 0) {
                d[i] = minOf(d[i], d[i - v] + 1)
            }
        }
    }
    println(if (d[k] == maxValue) -1 else d[k])
}