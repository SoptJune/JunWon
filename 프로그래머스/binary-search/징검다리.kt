fun main() {
    println(solution(25, intArrayOf(2, 14, 11, 21, 17), 2))
}

fun solution(DISTANCE: Int, rocks: IntArray, n: Int): Int {
    rocks.sort()
    val distances = IntArray(rocks.size + 1).apply {
        repeat(rocks.size - 1) { idx ->
            set(idx + 1, rocks[idx + 1] - rocks[idx])
        }
        set(0, rocks[0])
        set(lastIndex, DISTANCE - rocks[rocks.lastIndex])
    }
    var s = 1
    var e = DISTANCE
    var mid = (s + e) / 2
    var cur: Int
    var removedCnt: Int
    var ans = 0
    while (s <= e) {
        cur = 0
        removedCnt = 0
        distances.forEach { dist ->
            cur += dist
            if (cur < mid) {
                removedCnt++
            } else {
                cur = 0
            }
        }
        // 만약 n보다 더 많은 돌을 삭제했다면 탐색 범위 재설정
        if (removedCnt > n) {
            e = mid - 1
        } else {
            ans = mid
            s = mid + 1
        }
        mid = (s + e) / 2
    }
    return ans
}

