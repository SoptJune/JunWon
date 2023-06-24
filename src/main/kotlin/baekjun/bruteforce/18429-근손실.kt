import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

// ㅋㅋㅋㅋㅋ 3대 500을 유지해야한다. 난 언제쯤 500 찍으려나
// 항상 중량이 500 이상이 되도록 하는 경우의 수를 출력
// (1 ≤ N ≤ 8, 1 ≤ K ≤ 50)
// 브루트포스문제 ~ O(N^N) ~= 1600만
var ans = 0
var n = 0
var k = 0
private lateinit var weights: List<Int>
fun main() {
    val tmp = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    baekjun.n = tmp[0]
    k = tmp[1]
    weights = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    backTracking()
    bw.write("${baekjun.ans}")
    bw.flush()
    baekjun.tree.`4803`.br.close()
    bw.close()
}

private fun backTracking(depth: Int = 0, visited: Int = 0, cur: Int = 500) {
    if (depth == baekjun.n) {
        baekjun.ans++
        return
    }
    repeat(baekjun.n) { i ->
        if (visited and (1 shl i) == 0) {
            if (cur + weights[i] - k >= 500) {
                backTracking(depth + 1, visited or (1 shl i), cur + weights[i] - k)
            }
        }
    }
}
