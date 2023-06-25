package init

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = readLine()?.toInt()
    
    print(n)
    // baekjun.getN = baekjun.getBr.readLine().toInt()
    // baekjun.getAns = 0

    // bw.write("${baekjun.getAns}")
    // bw.flush()
    // baekjun.getBr.close()
    // bw.close()
}