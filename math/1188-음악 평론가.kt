package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

private var ans = 0
fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    ans = divideSausage(n, m)
    println(ans)
}

fun divideSausage(x: Int, y: Int): Int {
    var s = x
    val p = y
    // 만약 소세지 수가 사람 수보다 많을 경우 나머지 연산!!
    if (s > p) s %= p

    // case 1: 소세지 수가 사람 수로 나눠질 때
    if (s % p == 0) {
        return ans
    }

    // case 2: 사람 수가 소세지 수로 나눠질 경우
    if (p % s == 0) {
        // s, p = 2, 6
        // q = (6 / 2) = 3, 소세지 1개를 q - 1번 자르면 됨
        // (q - 1) * 소세지 개수 명의 사람들에게 소세지를 분배해줌 -> 분배 끝!
        val q = p / s
        ans += (q - 1) * s
        return ans
    }
    // case 3: 사람 수와 소세지 수가 나눠지지 않을 때
    val q = p / s
    // s, p = 3, 7
    // q = (7 / 3) = 2
    // q * 소세지 개수 명의 사람들에게 소세지를 분배해줌
    ans += q * s
    // 남은 소세지를 분배 받지 못한 사람들에게 배분~
    return divideSausage(s, p - q * s)
}