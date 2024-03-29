package baekjun.stack

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 2500 이니까 뭐 N^2도 가능~ 브루트포스 갈겨~
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

private var ans = 0
fun main() {
    val str = br.readLine()
    val target = br.readLine()
    var idx = 0
    while (idx <= str.length - target.length) {
        if (str.substring(idx, idx + target.length) == target) {
            ans++
            idx += target.length
        } else idx++
    }

    bw.write("$ans\n")
    bw.flush()
    br.close()
    bw.close()
}
