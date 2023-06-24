import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


var ans1 = 0
var ans2 = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val d = IntArray(n+1) { 0 }
    fibo(n)
    dpFibo(n, d)
    bw.write("$ans1 $ans2\n")
    bw.flush()
    bw.close()
    br.close()

}

private fun fibo(i: Int): Int {
    if (i == 1 || i == 2) {
        ans1++
        return 1
    }
    return fibo(i - 1) + fibo(i - 2)
}

private fun dpFibo(i: Int, d: IntArray): Int {
    if (i == 1 || i == 2) {
        return 1
    }
    if (d[i] > 0) {
        return d[i]
    }
    ans2++
    d[i] = dpFibo(i - 1, d) + dpFibo(i - 2, d)
    return d[i]
}