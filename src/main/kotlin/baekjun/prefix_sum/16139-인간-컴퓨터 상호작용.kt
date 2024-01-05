package baekjun.prefix_sum

import java.util.*

fun main() {
    val o = System.out.bufferedWriter()
    val r = System.`in`.bufferedReader()
    val str = r.readLine().map { it.code - 97 }
    val sb = StringBuilder()
    val cnts = List(26) { IntArray(str.size) }

    for (code in 0 until 26) {
        for (i in str.indices) {
            if (i == 0) {
                cnts[code][i] = 1.takeIf { str[i] == code } ?: 0
                continue
            }
            cnts[code][i] = cnts[code][i - 1] + (1.takeIf { str[i] == code } ?: 0)
        }
    }
    repeat(r.readLine().toInt()) {
        val r = StringTokenizer(r.readLine())
        val al = r.nextToken()[0].code - 97
        val (s, e) = r.nextToken().toInt() to r.nextToken().toInt()
        if (s == 0) {
            sb.append(cnts[al][e])
            sb.appendLine()
        } else {
            sb.append(cnts[al][e] - cnts[al][s - 1])
            sb.appendLine()
        }
    }
    o.write(sb.toString())
    o.flush()
    o.close()
}

