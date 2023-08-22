package baekjun.sort

fun main() {
    practiceJoinToString()
    val num = readln().toList().map { it.toString().toInt() }
    println(num.sortedBy { -it }.joinToString(""))
}

private fun practiceJoinToString() {
    println(listOf(1, 2, 3).joinToString(" ") {
        it.toString() + "0"
    }) // 10 20 30

    println(listOf(1, 2, 3).joinToString(prefix = "[", postfix = "]", separator = " ", limit = 2, truncated = "...") {
        it.toString() + "0"
    }) // [10 20 ...]
}