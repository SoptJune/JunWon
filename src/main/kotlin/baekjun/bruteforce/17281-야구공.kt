package baekjun.bruteforce
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
private var ans = 0
private lateinit var scoresTotal: MutableList<List<Int>>
val visited = BooleanArray(9)
fun main() {
    val n = br.readLine().toInt()
    scoresTotal = mutableListOf<List<Int>>().apply {
        repeat(n) {
            add(br.readLine().split(" ").map { it.toInt() })
        }
    }
    // 모든 경우의 수
    permutation()

    bw.write("${ans}")
    bw.flush()
}

/*
* 결국.. 메모이제이션 방식 + dfs로 풀었습니다 후...  */
private fun permutation(
    r: Int = 8,
    curArr: IntArray = IntArray(9)
) {
    if (r == 0) {
        startInning(curArr)
        return
    }
    for (i in 1..8) {
        if (!visited[i]) {
            visited[i] = true
            if (r <= 3) curArr[r - 1] = i
            else curArr[r] = i
            permutation(r - 1, curArr)
            visited[i] = false
        }
    }
}

private fun startInning(result: IntArray) {
    val battingOrders = result.apply { this[3] = 0 }
    var curIdx = 0
    var tmpCnt = 0
    // 1게임
    scoresTotal.forEach { scores ->
        var cnt = 3
        val runs = BooleanArray(4)
        // 1이닝
        while (cnt != 0) {
            runs[0] = true // 타자 game start
            val score = scores[battingOrders[curIdx % 9]] // 타자의 점수
            curIdx++
            if (score == 0) { // 아웃
                cnt--
                continue
            }
            repeat(4) { // 1루 2루 3루 홈
                val curPos = 3 - it
                if (runs[curPos]) {
                    if (curPos + score >= 4) {
                        tmpCnt++
                        runs[curPos] = false
                    } else {
                        runs[curPos + score] = true
                        runs[curPos] = false
                    }
                }
            }
        }
        ans = maxOf(ans, tmpCnt)
    }
}
