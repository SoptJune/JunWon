package baekjun.sort.`1431-시리얼 번호`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = br.readLine().toInt()
    val arr = arrayListOf<String>().apply { repeat(n) { add(br.readLine()) } }
    arr.sortedWith(compareBy({ it.length }, { str ->
        var cnt = 0
        str.forEach { if (it.code < 65) cnt += it.toString().toInt() }
        cnt
    }, { it })
    ).forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
    br.close()
}