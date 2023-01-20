fun main() {
    println(permutation(listOf(1,2,3,4,5))) 
    // 출력값 [[1, 2, 3, 4, 5], [1, 2, 3, 5, 4], [1, 2, 4, 3, 5] ... ]
    println(permutation(listOf(1,2,3,4,5), target = 5))
    // 출력값 : 위와 같음
    println(permutation(listOf(1,2,3,4,5), target = 4))
    // 출력값 : [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 3], ...
    println(permutation(listOf(1,2,3,4,5), target = 6))
    // 출력값 : []
}

/**
 * List<T>의 모든 순열을 구하는 함수
 *
 * @param T
 * @param r : 순열의 길이
 * @param cur : 현재까지의 순열
 * @return List<List<T>>
 */
fun <T> List<T>.permutation(r: Int = this.size, cur: List<T> = emptyList()): List<List<T>> {
    if (r == 0) return listOf(cur)
    return flatMap {
        (this - it).permutation(r - 1, cur + it)
    }
}

// 얘도 나중에 인덱싱 방식으로 바꿔야할듯.. 공간복잡도, 시간복잡도 너무 많이 사용