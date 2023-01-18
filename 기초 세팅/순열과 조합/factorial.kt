fun main() {
    println(factorial(5)) // 120
}

tailrec fun factorial(n: Int, result: Int): Int {
    if (n == 1) return result
    return factorial(n - 1, result * n)
}