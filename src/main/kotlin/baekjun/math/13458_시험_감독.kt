package baekjun.math

// 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
// 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.
// B와 C가 주어진다. (1 ≤ B, C ≤ 1,000,000)
fun main() {
    // 시험장 수
    val n = readln().toInt()
    // 응시자 수
    val stds = readln().split(" ").map { it.toInt() }
    // B : 총감독관 감시수, C : 부감독관 감시수
    val (b, c) = readln().split(" ").map { it.toInt() }
    // 정답
    var ans = stds.size.toLong()
    stds.map { it - b }
        .filter { it > 0 }
        .forEach { num ->
        val cnt = (num / c).takeIf { num % c == 0 } ?: ((num / c) + 1)
        ans += cnt
    }
    println(ans)
}