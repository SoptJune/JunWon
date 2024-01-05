package calculator

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val cnt = br.readLine().toInt()
    val arr = ArrayList<String>()
    repeat(cnt) {
        arr.add(br.readLine())
    }
    arr.distinct().sortedWith(
        compareBy<String> { it.length }.thenBy { it }
    ).forEach { bw.write("$it\n") }

    bw.flush()
    bw.close()
    br.close()
}
