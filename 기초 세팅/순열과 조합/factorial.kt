fun main() {
    println(factorial(5)) // 120
}

/** Int */
tailrec fun factorial(n: Int, result: Int = 1): Int {
    if (n == 1) return result
    return factorial(n - 1, result * n)
}

/** BigInteger */
tailrec fun factorial(n: Int, ans: BigInteger = BigInteger("1")): BigInteger {
    if (n == 1) return ans
    return factorial(n - 1, ans.multiply(BigInteger("$n")))
}