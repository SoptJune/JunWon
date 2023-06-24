fun main() {
    println(solution(numbers = intArrayOf(979, 97, 978, 81, 818, 817)))
}

// numbers의 원소는 0 이상 1,000 이하
private fun solution(numbers: IntArray): String {
    return if (numbers.all { it == 0 }) "0"
    else numbers
        .sortedWith { a, b ->
            compareValuesBy("$b$a", "$a$b") {
                it
            }
        }
        .joinToString("") { it.toString() }
}