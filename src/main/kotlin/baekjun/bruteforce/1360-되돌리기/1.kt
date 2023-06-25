package baekjun.bruteforce.`1360-되돌리기`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
lateinit var graph: MutableList<MutableList<Int>>
lateinit var visited: BooleanArray
fun main() {
    val n = br.readLine().toInt()
    val stack: MutableList<List<String>> = mutableListOf<List<String>>().apply {
        repeat(n) {
            add(br.readLine().split(" "))
        }
    }
    var curTime = stack[stack.lastIndex][2].toInt()
    val ans = StringBuilder("")

    while (stack.isNotEmpty()) {
        val (op, char, t) = stack.removeLast()
        val time = t.toInt()
        if (curTime >= time) {
            if (op == "type") {
                ans.insert(0, char)
                curTime--
            } else {
                val undoTime = char.toInt() // 헷갈려서 변수 지정했어요
                curTime = time - undoTime - 1
            }
        } else continue
    }
    bw.write("$ans\n")
    bw.flush()
    bw.close()
    br.close()
}
