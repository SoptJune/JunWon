package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

// 계단 1 or 2계단
// 1) 마지막 계단 밟아야함
// 2) 연속된 세 개의 계단 모두 밟으면 안됨 -> 2번 연속만 된다
// 2계단 점프 : An + d[n-2]
// 1계단 점프 : An + An-1 + d[n-3] 
// 점화식 d[n] = An + max ( d[n-2] , An-1 + d[n-3] )
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

val d = IntArray(300 + 1)
val stairs = IntArray(300 + 1)
fun main() {
    val n = br.readLine().toInt()
    stairs.also { arr ->
        repeat(n) { i ->
            arr[i + 1] = br.readLine().toInt()
        }
    }
    d[1] = stairs[1]; d[2] = stairs[1] + stairs[2]
    for (i in 3 until n + 1) {
        d[i] = stairs[i] + max(d[i - 2], stairs[i - 1] + d[i - 3])
    }
    bw.write("${d[n]}")
    bw.flush()
    br.close()
    bw.close()
}
