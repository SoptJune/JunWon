package baekjun.bruteforce.`1157_단어공부`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// O(N)
// 뭔가 좀 더 간결화할 수 있을 것 같긴 한데...
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val str = br.readLine()
    var ans = ""
    var maxAlphabetCnt = 0
    val pivot: Int?
    val alphabetArr = IntArray(26) { 0 }

    for (s in str) {
        val tmp = s.toChar().code
        if (tmp >= 97) {
            alphabetArr[tmp - 97]++
        } else {
            alphabetArr[tmp - 65]++
        }
    }
    pivot = alphabetArr.maxOrNull()
    for (i in 0 until 26) {
        if (alphabetArr[i] == pivot) {
            ans = (65 + i).toChar().toString()
            maxAlphabetCnt += 1
        }
    }
    if (maxAlphabetCnt == 1) bw.write(ans) else bw.write("?")
    bw.flush()
    br.close()
    bw.close()
}
