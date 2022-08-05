import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val d = IntArray(n + 1) { 0 }
    for (i in 2..n) {
        // 1) -1을 한 경우
        d[i] = d[i-1] +1

        // 2) 3으로 나눈 경우
        if (i % 3 == 0) {
            d[i] = minOf(d[i / 3] + 1, d[i])
        }

        // 3) 2로 나눈 경우
        if (i % 2 == 0) {
            d[i] = minOf(d[i / 2] + 1, d[i])
        }
    }

    val ans = d[n]
    bw.write("$ans\n")
    bw.flush()
    bw.close()
    br.close()

}

