import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val parent = br.readLine()
    val subString = br.readLine()
    kmp(parent, subString)

    bw.flush()
    br.close()
    bw.close()
}

private fun getTable(pattern: String): IntArray {
    val patternSize = pattern.length
    val patternTable = IntArray(patternSize)
    var j = 0
    for (i in 1 until patternSize) {
        while (j > 0 && pattern[i] != pattern[j]) {
            j = patternTable[j - 1]
        }
        if (pattern[i] == pattern[j]) {
            patternTable[i] = ++j
        }
    }
    return patternTable
}

private fun kmp(parent: String, pattern: String) {
    val parentSize = parent.length
    val patternSize = pattern.length
    val patternTable = getTable(pattern)
    var j = 0

    for (i in 0 until parentSize) {
        while (j > 0 && parent[i] != pattern[j]) {
            j = patternTable[--j]
        }
        if (parent[i] == pattern[j]) {
            if (j == patternSize - 1) {
                bw.write("1")
                return
            }
            j++
        }
    }
    bw.write("0")
    return
}

