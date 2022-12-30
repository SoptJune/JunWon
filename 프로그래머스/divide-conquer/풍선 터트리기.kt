import kotlin.math.max

fun main() {
    println(solution(intArrayOf(9, -1, -5)))
    println(solution(intArrayOf(-16, 27, 65, -2, 58, -92, -71, -68, -61, -33)))
}
   // 배열 길이 : 1 .. 1e6
    // a의 수 범위 : -1e9 ~ 1e9 정수 범위
    // 모든 수들은 중복 X
    // 음.. 완탐은 불가능.. O(N or NlogN)만족해야함 
    // 정렬, 최단거리, 이분탐색? X
    // 분할정복 or dp 같음
    // dp는 진짜 아닌 것 같다.. 작은 문제들이 합쳐서 큰 문제가 되는 게 아니므로..
    // 분할 정복으로 가자
    // l now r 으로 분할해보자 --> 매 번 l, r으로 배열을 순회하면서 최솟값 찾는다면 완탐 풀이랑 동일하게 O(N^2)이 걸린다.. 캐싱하자
fun solution(nums: IntArray): Int {
    val N = nums.size
    if (N == 1 || N == 2) return N
    // nums[0 .. idx]의 최솟값을 저장한 배열
    val leftMinVs = IntArray(N) { (1e6).toInt() + 1 }.apply { this[0] = nums[0] }
    // nums[idx .. N - 1]의 최솟값을 저장한 배열
    val rightMinVs = IntArray(N) { (1e6).toInt() + 1 }.apply { this[N - 1] = nums[N - 1] }
    // 왜 set을 했지 난.. 풀고 나니까 왜 set으로 했는지... 그냥 var ans = 0 더 좋았네용..
    val ansSet = mutableSetOf<Int>(nums[0], nums[N-1])
    // leftMinVs, rightMinVs 초기화
    repeat(N - 1) {
        val idx = it + 1
        leftMinVs[idx] = leftMinVs[idx].coerceAtMost(leftMinVs[idx - 1])
        rightMinVs[(N - 1) - idx] = rightMinVs[(N - 1) - idx].coerceAtMost(rightMinVs[(N - 1) - idx + 1])
    }
    // leftMinValue, curNum, rightMinValue 중 curNum이 가장 큰 수만 아니면 최후의 풍선이 가능합니다.
    // 이 조건을 노가다로 찾았네용..
    for (i in 1..N - 2) {
        if (nums[i] != max(leftMinVs[i - 1], max(nums[i], rightMinVs[i + 1]))) ansSet.add(nums[i])
    }
    // println(ansSet)
    return ansSet.size 
}
