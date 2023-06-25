package baekjun.bruteforce
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
// 7
// 2 2 7 6 90 5 9
// 8!
private var ans = 0
fun main() {
    val n = br.readLine().toInt()
    br.readLine().split(" ").map{it.toInt()}.permutation()
    bw.write("${ans}")
    bw.flush()
    bw.close()
    br.close()
}

fun List<Int>.permutation(
    r: Int = 0,
    cur: Int = 0,
    visited: Int = 0
) {
    if (r == this.size - 2) {
        ans = maxOf(ans, cur)
        return
    }
    for (i in 1..(this.size - 2)) {
        if (visited and (1 shl i) == 0) {
            var s = i - 1
            while (visited and (1 shl s) != 0) { // 만약 이미 제거된 구슬이면 왼쪽으로 한칸 이동
                s--
            }
            var e = i + 1
            while (visited and (1 shl e) != 0) { // 만약 이미 제거된 구슬이면 오른쪽으로 한칸 이동
                e++
            }
            val result = this[s] * this[e]
            permutation(r + 1, cur + result, visited or (1 shl i))
        }
    }
}