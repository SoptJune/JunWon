class Solution {
    fun main() {
    println(solution(6, intArrayOf(7, 10)))
    println(solution(1e9.toInt(), IntArray(100_000) { 1e9.toInt() }).toString().length)
}

// n의 범위 : 1 ~ 1e9
// 입국 심사시간 : 1 ~ 1e9
// 입국 심사관 : 1 ~ 1e5

// O(nlogn) or O(baekjun.getN), 느낌상 그리디 문제 혹은 dp문제 같음
// 오 이분탐색이네 - 파라매트릭스 문제!!
// 복잡도 O(10만 * log10^14)
// 10^9명이 심사 10^5명한테 심사 모든 심사시원 심사시간 10^9인 경우 - baekjun.getAns = 10^13
// Long으로 추웅분
private lateinit var times: IntArray
fun solution(target: Int, ts: IntArray): Long {
    times = ts
    var s = 1L
    var e = 1e13.toLong()
    var mid = 0L
    var curCnt = 0L
    while (s <= e) {
        mid = (s + e) / 2
        if (sumOfPeopleCnt(mid) >= target) {
            e = mid - 1
            curCnt = mid
        } else {
            s = mid + 1
        }
    }
    return curCnt
}

private fun sumOfPeopleCnt(time: Long) = times.sumOf { time / it.toLong() }
}