package baekjun.bruteforce.n과m
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 1 ≤ M ≤ N ≤ 8
// nCm 순서대로 나열
// 백트래킹
// O(8!) = 약 4만의 연산
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val visited = mutableListOf<Boolean>().apply {
        repeat(n + 1) {
            add(false)
        }
    }
    backTracking(n, m, mutableListOf<Int>(), 0, visited)
    bw.flush()
    br.close()
    bw.close()
}

fun backTracking(n: Int, m: Int, ans: MutableList<Int>, num: Int, visited: MutableList<Boolean>) {
    if (num == m) {
        bw.write("" + ans.joinToString(" ") + "\n")
        return
    }
    for (i in 1..n) {
        if (!visited[i]) {
            visited[i] = true
            ans.add(i)
            backTracking(n, m, ans, num + 1, visited)
            visited[i] = false
            ans.removeAt(ans.size - 1)
        }
    }
}
