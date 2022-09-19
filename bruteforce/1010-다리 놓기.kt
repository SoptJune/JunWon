import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    repeat(br.readLine().toInt()) {
        val (m, n) = br.readLine().split(" ").map { it.toInt() }
        bw.write("${combination(n, m)}\n")
        bw.flush()
    }
    br.close()
    bw.close()
}

private fun combination(n: Int, m: Int) =
    factorial(n).divide(factorial(m)).divide(factorial(n - m))

private fun factorial(n: Int): BigInteger {
    var ans = BigInteger("1")
    for (i in 2..n) {
        ans = ans.multiply(BigInteger(i.toString()))
    }
    return ans
}