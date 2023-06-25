package baekjun.dp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 점화식 A[baekjun.getN] = min(A[baekjun.getN][0], A[baekjun.getN][1], A[baekjun.getN][2]))
// rgb 2
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = br.readLine().toInt()
    val rgb = Array(n) { IntArray(3) }
    var ans = (1e9).toInt()
    for (i in 0 until n) {
        val (r, g, b) = br.readLine().split(" ").map { it.toInt() }
        rgb[i][0] = r
        rgb[i][1] = g
        rgb[i][2] = b
    }
    repeat(3) { firstColor -> // 첫 번째 색 고정
        val d = Array(n) { IntArray(3) }
        d[0][firstColor] = rgb[0][firstColor]
        d[0][(firstColor + 1) % 3] = (1e9).toInt()
        d[0][(firstColor + 2) % 3] = (1e9).toInt()
        for (i in 1 until n) {
            d[i][0] = minOf(d[i - 1][1], d[i - 1][2]) + rgb[i][0]
            d[i][1] = minOf(d[i - 1][0], d[i - 1][2]) + rgb[i][1]
            d[i][2] = minOf(d[i - 1][0], d[i - 1][1]) + rgb[i][2]
        }
        // 첫 번째 색, 마지막 색 같으면 거르기
        ans = minOf(ans, d[n - 1][(firstColor + 1) % 3], d[n - 1][(firstColor + 2) % 3])
    }
    bw.write("$ans")
    bw.flush()
    bw.close()
    br.close()
}