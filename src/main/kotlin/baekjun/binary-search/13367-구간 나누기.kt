import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// M개 이하의 구간으로 나눈다.
// (1 ≤ N ≤ 5,000, 1 ≤ M ≤ N) -> O(N^2)보다 적은 시간 복잡도를 갖는 알고리즘을 설계해야함
// 1. 하나의 구간은 하나 이상의 연속된 수들로 이루어져 있다.
// 2. 배열의 각 수는 모두 하나의 구간에 포함되어 있어야 한다.
// 3. 구간의 점수의 최댓값의 최솟값
// 4. 1보다 크거나 같고, 10,000보다 작거나 같은 자연수
// 완탐을 하면서 동적으로 각 구간을 동적으로 바꿔가며 풀면 시간초과가 날 것 같다.
// dp 문제라고 하기엔 중복되는 구간이 없음 나무자르기랑 비슷한 문제 인거 같기도 하고..

// 파라매트릭스로 mid값 정하고, 투포인터 알고리즘으로 mid보다 (maxValue - minValue)를
// 만족하는 구간의 수가 m 이하 인 구간들을 찾는다.

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val (_, m) = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    val nums = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    var ans = 10_000
    var (s, e) = listOf(0, 10_001)
    // 파라매트릭스 문제
    while (s <= e) {
        val mid = (s + e) / 2
        if (m >= countSelection(mid, nums) + 1) { // 여기서 1은 mid를 포함하는 구간의 수
            e = mid - 1
            ans = mid.coerceAtMost(ans)
        } else {
            s = mid + 1
        }
    }
    bw.write("$ans")
    bw.flush()
    baekjun.tree.`4803`.br.close()
    bw.close()
}

// 해당 배열의 구간 수를 반환하는 함수
private val countSelection: (Int, List<Int>) -> Int = { mid, nums ->
    var cnt = 0
    var minV = nums[0]
    var maxV = nums[0]
    // 투 포인터 알고리즘
    nums.forEachIndexed { idx, num ->
        minV = num.coerceAtMost(minV)
        maxV = num.coerceAtLeast(maxV)
        if (maxV - minV > mid) { // 만약, 해당 구간에서 diff(최대값 - 최소값) > pivot 조건을 만족한다면
            cnt++ // 구간 수 + 1
            minV = nums[idx] // 이제 새로운 구간 찾기
            maxV = nums[idx]
        }
    }
    cnt // mid보다 diff가 큰 구간의 수
}
