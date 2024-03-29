package baekjun.backtracking.`9663-n-queen`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
private val n = br.readLine().toInt()
private val graph = IntArray(n)
private var ans = 0
fun main() {
    searchQueen(0)
    bw.write("${ans}\n")
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
            searchQueen(y + 1) // 다음 y축에서 퀸 찾기 
            graph[y] = 0
        }
    }
}

/**
 *  퀸을 해당 좌표에 놓을 수 있는지 판별하는 함수
 *  즉, 이전 퀸들과 서로 공격이 가능한지 확인하는 함수다.
 * */
private fun isPossible(x: Int, y: Int): Boolean {
    // Q_Y, Q_x 는 지금 위치 이전에 있는 퀸의 좌표
    for (q_y in 0 until y) {
        val q_x = graph[q_y] // 이전 queen의 x좌표

        // 1) x축 좌표가 겹치는지 체크
        // 2) 대각선 방향으로 공격경로가 겹치는지 체크
        if (q_x == x || abs(x - q_x) == y - q_y)
            return false
    }
    return true
}