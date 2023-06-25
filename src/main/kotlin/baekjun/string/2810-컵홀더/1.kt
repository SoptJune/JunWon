package baekjun.string

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = br.readLine().toInt()
    val seats = br.readLine()
    var cnt = 1 // 처음 좌석 왼쪽에 컵홀더가 1개 있으니 1부터 시작
    var idx = 0
    // 컵 홀더의 수는 좌석의 오른쪽만 세도록하자
    while (idx < n) {
        if (seats[idx] == 'S') {
            cnt++
            idx++
        } else {
            cnt++
            idx += 2
        }
    }
    val ans = if (n < cnt) n else cnt // 만약 사람 수 보다 좌석수가 많으면 사람수
    bw.write("$ans\n")
    bw.flush()
    bw.close()
    br.close()
}

