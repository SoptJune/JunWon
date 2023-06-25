package programmers.bruteforce
// 1만 * 1만 * 2 - 완전 탐색은 안되고 조건으로 걸려야할듯?
// 1. 모든 card의 w, h중 가장 긴 길이(maxLen)를 찾는다.
// 2. 나머지 card들의 중 w, h중 긴 쪽을 maxLen 방향으로 돌린다.
// 3. 모든 card들의 짧은 쪽 길이들 중 가장 긴 녀석의 len * maxLen 가 답이다.

private fun solution(sizes: Array<IntArray>): Int {
    var maxLen = 0 // w, h
    var maxLenAt = -1
    val shortLenList = sizes
        // 1. 모든 card의 w, h중 가장 긴 길이(maxLen)를 찾는다.
        .onEach { size -> maxLen = maxLen.coerceAtLeast(size[0].coerceAtLeast(size[1])) }
        // 1 - 1. maxLen가 w인지 h인지 check, index가 0 이면 w, index가 1이면 h
        .also { mSizes ->
            mSizes.onEach { size ->
                maxLenAt = size.indexOfFirst { it == maxLen }
                if (maxLenAt != -1) return@also
            }
        }
        // 2. 나머지 card들의 중 w, h중 긴 쪽을 maxLen 방향으로 돌린다.
        .map { size ->
            val w = size[0]
            val h = size[1]
            if (w > h) {
                if (maxLenAt == 0) return@map size
                return@map intArrayOf(h, w)
            }
            if (maxLenAt == 1) return@map size
            return@map intArrayOf(h, w)
        }
        // 3. 짧은 쪽의 길이로만 이뤄진 list를 뽑아낸다.
        .map { size ->
            size[1 - maxLenAt]
        }
    // 4. 모든 card들의 짧은 쪽 길이들(shortLenList) 중 가장 긴 녀석의 len * maxLen 가 답이다.
    return maxLen * shortLenList.maxOrNull()!!
}