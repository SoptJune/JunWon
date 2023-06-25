package baekjun.backtracking
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

//  단순할 확률을 출력
// 절대/상대 오차는 10-9
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
lateinit var visited: MutableList<BooleanArray>
private val dx = listOf(0, 0, 1, -1)
private val dy = listOf(1, -1, 0, 0)
private var ans: Double = 0.0
lateinit var percentageList: List<Double>
fun main() {
    val (n, E, W, N, S) = br.readLine().split(" ").map { it.toInt() }
    percentageList = listOf(E * 0.01, W * 0.01, N * 0.01, S * 0.01)
    visited= mutableListOf<BooleanArray>().apply {
        repeat(29) {
            add(BooleanArray(29))
        }
    }
    visited[14][14] = true
    backTracking(n, 14, 14, 0, 1.0)
    bw.write("${ans}\n")
    bw.flush()
    bw.close()
    br.close()
}

fun backTracking(n: Int, x: Int, y: Int, cnt: Int, result: Double) {
    if (cnt == n) {
        ans += result
        return
    }

    repeat(4) { i ->
        val nx = x + dx[i]
        val ny = y + dy[i]
        // 범위 벗어나지 않고, 단순할 경우에만 검사
        if (0 <= nx && nx < 29 && 0 <= ny && ny < 29 && !visited[nx][ny]) {
            visited[nx][ny] = true
            backTracking(n, nx, ny, cnt + 1, result * percentageList[i])
            visited[nx][ny] = false
        }
    }
}
