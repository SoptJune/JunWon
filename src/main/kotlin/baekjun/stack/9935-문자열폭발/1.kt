package baekjun.stack.`9935-문자열폭발`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
private var stack = StringBuilder()
private const val EMPTY_STRING = "FRULA"
fun main() {
    val string = br.readLine()
    val bombString = br.readLine() // length = 36
    val size = bombString.length

    string.forEach { chr ->
        stack.append(chr)
        while (stack.length >= size && stack.subSequence(stack.lastIndex - size + 1..stack.lastIndex) == bombString) {
            stack = stack.delete(stack.lastIndex - size + 1, stack.lastIndex + 1)
        }
    }
    bw.write(if (stack.isNotBlank()) stack.toString() else EMPTY_STRING)
    bw.flush()
    br.close()
    bw.close()
}
