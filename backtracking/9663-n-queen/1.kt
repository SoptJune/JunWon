import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
val n = br.readLine().toInt()
val graph = IntArray(n)
var ans = 0
fun main() {
    searchQueen(0)
    bw.write("$ans\n")
    bw.flush()
    bw.close()
    br.close()

}

private fun searchQueen(y: Int) {
    if (y == n) {
        ans++
        return
    }

    for (x in 0 until n) {
        if (isPossible(x, y)) {
            graph[y] = x
            searchQueen(y + 1)
            graph[y] = 0
        }
    }
}

/**
 * 현재 좌표 (x,y)에 queen을 놓을 수 있는가??
 * */
private fun isPossible(x: Int, y: Int): Boolean {
    for (q_y in 0 until y) {
        val q_x = graph[q_y] // 이전 queen의 x좌표

        // 1) x축 좌표가 겹치는지 체크
        // 2) 대각선 방향으로 공격경로가 겹치는지 체크
        if (q_x == x || abs(x - q_x) == y - q_y)
            return false
    }
    return true
}