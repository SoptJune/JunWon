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

// 아래 녀석들 사용하면 됨!!

// Int List
private fun List<Int>.permutation(
    r: Int = this.size,
    curArr: MutableList<Int> = MutableList(r) { 0 },
    visited: Int = 0
) {
    if (r == 0) {
        println(curArr)
        return
    }
    forEachIndexed { i, v ->
        if (visited and (1 shl i) == 0) {
            curArr[curArr.size - r] = v
            permutation(r - 1, curArr, visited or (1 shl i))
        }
    }
}

// String List
private fun List<String>.permutation(
    r: Int = this.size,
    curArr: MutableList<String> = MutableList(r) { "" },
    visited: Int = 0
) {
    if (r == 0) {
        println(curArr)
        return
    }
    forEachIndexed { i, v ->
        if (visited and (1 shl i) == 0) {
            curArr[curArr.size - r] = v
            permutation(r - 1, curArr, visited or (1 shl i))
        }
    }
}

// Int List
// ex) println(listOf(1, 2, 3).permutationAll())
// output : [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
private fun List<Int>.permutationAll(
    r: Int = this.size,
    curArr: MutableList<Int> = MutableList(r) { 0 },
    visited: Int = 0,
    result: MutableList<List<Int>> = mutableListOf()
): List<List<Int>> {
    if (r == 0) {
        result.add(curArr.toList())
        return emptyList()
    }
    forEachIndexed { i, v ->
        if (visited and (1 shl i) == 0) {
            curArr[curArr.size - r] = v
            permutationAll(r - 1, curArr, visited or (1 shl i), result)
        }
    }
    return result
}

// String List
// ex) println(listOf("1", "2", "3").permutationAll())
// output : [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
private fun List<String>.permutationAll(
    r: Int = this.size,
    curArr: MutableList<String> = MutableList(r) { "" },
    visited: Int = 0,
    result: MutableList<List<String>> = mutableListOf()
): List<List<String>> {
    if (r == 0) {
        result.add(curArr.toList())
        return emptyList()
    }
    forEachIndexed { i, v ->
        if (visited and (1 shl i) == 0) {
            curArr[curArr.size - r] = v
            permutationAll(r - 1, curArr, visited or (1 shl i), result)
        }
    }
    return result
}