import kotlin.math.sqrt

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun initTable() {
    for (i in 2..sqrt(1_000_000.0).toInt()) {
        if (!table[i]) continue
        for (j in i + i..1_000_000 step i) {
            table[j] = false
        }
    }
}

val table = BooleanArray(1_000_000 + 1) { true }.apply {
    this[0] = false; this[1] = false
}

fun provideFormattedAnswer(n: Int, m: Int, k: Int) = "$n = $m + $k"
fun main() = run {
    initTable()
    while (true) {
        val n = baekjun.tree.`4803`.br.readLine().toInt()
        if (n == 0) return@run
        for (i in 3..n) {
            if (table[i] && table[n - i]) {
                bw.write(provideFormattedAnswer(n, i, n - i) + "\n")
            }
            break
        }
        bw.flush()
    }
}