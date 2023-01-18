/**
 * nCr = n-1Cr-1 + n-1Cr 을 이용한 재귀함수
 * @param r 뽑을 갯수
 * @return 조합의 결과
 *
 * @sample
 * listOf(1, 2, 3, 4, 5).combinations(3) // [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5], [2, 3, 4], [2, 3, 5], [2, 4, 5], [3, 4, 5]]
 * listOf(1, 2, 3, 4, 5).combinations(2) // [[1, 2], [1, 3], [1, 4], [1, 5], [2, 3], [2, 4], [2, 5], [3, 4], [3, 5], [4, 5]]
 * listOf(1, 2, 3, 4, 5).combinations(1) // [[1], [2], [3], [4], [5]]
 * listOf(1, 2, 3, 4, 5).combinations(0) // []
 * listOf(1, 2, 3, 4, 5).combinations(5) // [[1, 2, 3, 4, 5]]
 * listOf(1, 2, 3, 4, 5).combinations(6) // [[]]
 * listOf(1, 2, 3, 4, 5).combinations(-1) // [[]]
 */
fun <T> List<T>.combinations(r: Int = this.size): List<List<T>> {
    if (r <= 0 || r > this.size) return listOf(emptyList())
    if (r == size) return listOf(this)
    if (r == 1) return map { listOf(it) }

    return drop(1).combinations(r - 1).map { listOf(first()) + it } + drop(1).combinations(r)
}

// 근데.. drop할 때마다 새로운 list를 만들기 때문에 비효율적인 거 같음..
// indexing 혹은 queue 자료구조로 develop하는게 더 좋을 것 같음
// 아마 indexing이 가장 빠를듯..