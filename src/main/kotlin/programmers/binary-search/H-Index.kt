package programmers.`binary-search`
fun main() {
    println(
        solution(intArrayOf(3, 0, 6, 1, 5))
    )
}

// 배열에서 h번 이상 h번 이하로 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하로 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.
// citations size (1 .. 1000)
// element size (0 .. 10_000)
// 파라매트릭스 문제
private lateinit var citations: IntArray
fun solution(arr: IntArray): Int {
    citations = arr
    var answer = 0
    var (s, e) = listOf(0, 1001)
    while (s <= e) {
        val mid = (s + e) / 2
        if (isValid(mid)) {
            answer = mid
            s = mid + 1
        } else {
            e = mid - 1
        }
    }
    return answer
}

fun isValid(h: Int): Boolean {
    return citations.filter { it >= h }.size >= h
}
