import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val d = IntArray(11) { 0 }
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    // 초깃값 세팅
    d[1] = 1
    d[2] = 2
    d[3] = 4
    for (i in 4..10) {
        d[i] = d[i - 1] + d[i - 2] + d[i - 3] // 점화식
    }
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        val ans = d[n]
        bw.write("$ans\n")
    }
    bw.flush()
    bw.close()
    br.close()

}

