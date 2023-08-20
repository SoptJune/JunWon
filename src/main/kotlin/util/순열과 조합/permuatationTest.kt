import java.lang.System.*

fun main() {
    // n이 10일 때
    /**
     * permutationAll() - 0.0401601792
     * permutation() - 0.160774025
     * permutation2() - 0.0148361625
     */
    var startTime = nanoTime()
    (1..10).toList().permutationAll()
    var endTime = nanoTime()
    println((endTime - startTime) / 1e10)
    startTime = nanoTime()
    (1..10).toList().permutation()
    endTime = nanoTime()
    println((endTime - startTime) / 1e10)
    startTime = nanoTime()
    (1..10).toList().permutation2()
    endTime = nanoTime()
    println((endTime - startTime) / 1e10)

    // n이 11일 때
    /**
     * permutationAll() - 0.4733596791
     * permutation() - 2.3766230625
     * permutation2() - 0.1956449166
     */
    startTime = nanoTime()
    (1..11).toList().permutationAll()
    endTime = nanoTime()
    println("permutationAll() - ${(endTime - startTime) / 1e10}")
    startTime = nanoTime()
    (1..11).toList().permutation()
    endTime = nanoTime()
    println("permutation() - ${(endTime - startTime) / 1e10}")
    startTime = nanoTime()
    (1..11).toList().permutation2()
    endTime = nanoTime()
    println("permutation2() - ${(endTime - startTime) / 1e10}")
}

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

private fun <T> List<T>.permutation(r: Int = this.size, cur: List<T> = emptyList()): List<List<T>> {
    if (r == 0) return listOf(cur)
    return flatMap {
        (this - it).permutation(r - 1, cur + it)
    }
}

private fun List<Int>.permutation2(
    r: Int = this.size,
    curArr: MutableList<Int> = MutableList(r) { 0 },
    visited: Int = 0
) {
    if (r == 0) {
//        println(curArr)
        return
    }
    forEachIndexed { i, v ->
        if (visited and (1 shl i) == 0) {
            curArr[curArr.size - r] = v
            permutation2(r - 1, curArr, visited or (1 shl i))
        }
    }
}