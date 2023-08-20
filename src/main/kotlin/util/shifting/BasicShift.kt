package util.shifting


/**
 * 오른쪽으로 한칸 이동
 * */
private fun IntArray.shift(): IntArray {
    val last = last()

    repeat(size - 1) {
        set(it + 1, get(it))
    }
    set(0, last)
    return this
}

/**
 * copy와 indexing 기법으로 구현
 * */
private fun IntArray.shift(amount: Int): IntArray {
    val _arr = this.copyOf()
    val provideIdx: (Int) -> Int = { (it + size + (amount % size)) % size }
    repeat(size) { i ->
        val idx = provideIdx(i)
        this[idx] = _arr[i]
    }

    return this
}

/**
 * slice로 시프트 구현
 * */
private fun List<Int>.shift(amount: Int): List<Int> {
    val a = (size + (amount % size)) % size

    return slice(size - a until size) + slice(0 until size - a)
}

fun main() {
    println(intArrayOf(0, 1, 2, 3, 4).shift().toList()) // [5, 1, 2, 3, 4]
    // list
    println(listOf(1, 2, 3, 4, 5).shift(1))
    println(listOf(1, 2, 3, 4, 5).shift(6))
    println(listOf(1, 2, 3, 4, 5).shift(-6))
    // array
    println(intArrayOf(1, 2, 3, 4, 5).shift(1).toList())
    println(intArrayOf(1, 2, 3, 4, 5).shift(5).toList())
    println(intArrayOf(1, 2, 3, 4, 5).shift(-10).toList())
    println(intArrayOf(1, 2, 3, 4, 5).shift(-1).toList())
}