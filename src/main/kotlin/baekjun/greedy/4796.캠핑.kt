package baekjun.greedy

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    with(br) {
        var cnt = 0
        while (true) {
            cnt++
            val list: List<Int> = readLine().split(" ").map { it.toInt() }
            if (list == listOf(0, 0, 0)) break
            val ans = (list[2] / list[1]) * list[0] + min(list[0], (list[2] % list[1]))
            bw.write("Case $cnt: $ans\n")
            bw.flush()
        }
        bw.close()
        close()
    }
}