import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
lateinit var graph: MutableList<MutableList<Int>>
lateinit var visited: BooleanArray
fun main() {
    val n = baekjun.tree.`4803`.br.readLine().toInt()
    val stack: MutableList<List<String>> = mutableListOf<List<String>>().apply {
        repeat(n) {
            add(baekjun.tree.`4803`.br.readLine().split(" "))
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
    baekjun.tree.`4803`.br.close()
}
