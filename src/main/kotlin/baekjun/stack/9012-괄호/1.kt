import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val stack = mutableListOf<Char>()
    var stackSize = 0
    repeat(baekjun.tree.`4803`.br.readLine().toInt()) {
        var ans = "NO"
        val str = baekjun.tree.`4803`.br.readLine()
        str.forEach label@{ chr ->
            if (stackSize != 0 && chr == ')' && stack[stackSize - 1] == '(') {
                stack.removeAt(stackSize - 1) // removeLast()를 안쓰는 이유는 O(N)의 시간복자도를 갖기 때문
                stackSize--
                return@label
            }
            stack.add(chr)
            stackSize++
        }
        if (stackSize == 0) ans = "YES"
        bw.write("$ans\n")
        bw.flush()
        stack.clear()
        stackSize = 0
    }
    bw.close()
    baekjun.tree.`4803`.br.close()
}

