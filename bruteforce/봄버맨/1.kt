import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
val dx: List<Int> = listOf(1, 0, 0, -1)
val dy: List<Int> = listOf(0, -1, 1, 0)
const val BOMB = "O"
const val EMPTY = "."
val timeTable: List<IntArray> = mutableListOf<IntArray>().apply {
    repeat(200) {
        add(IntArray(200))
    }
}
private lateinit var graph: List<MutableList<String>>
fun main() {
    val (n, m, LIMIT) = br.readLine().split(" ").map {
        it.toInt()
    }
    graph = mutableListOf<MutableList<String>>().apply {
        repeat(n) { i ->
            add(br.readLine().toList().mapIndexed { j, value ->
                val tmp = value.toString()
                tmp
            }.toMutableList())
        }
    }
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (graph[i][j] == BOMB) timeTable[i][j]++
        }
    }
    repeat(LIMIT - 1) {
        traverseGraph(n, m)
    }
    graph.forEach { row ->
        bw.write("${row.joinToString("")}\n")
    }
    bw.flush()
    br.close()
    bw.close()
}

private fun traverseGraph(n: Int, m: Int) {
    // 빈 곳에는 폭탄 설치, 폭탄 있는 곳은 +1
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (graph[i][j] == BOMB) timeTable[i][j] += 1
            else graph[i][j] = BOMB
        }
    }
    // 3초 지난 폭탄 터트리기
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (graph[i][j] == BOMB && timeTable[i][j] == 3) boom(n, m, i, j)
        }
    }
}

private fun boom(n: Int, m: Int, x: Int, y: Int) {
    graph[x][y] = EMPTY
    timeTable[x][y] = 0
    repeat(4) { i ->
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (nx in 0 until n && ny in 0 until m && timeTable[nx][ny] < 3) {
            graph[nx][ny] = EMPTY
            timeTable[nx][ny] = 0
        }
    }
}