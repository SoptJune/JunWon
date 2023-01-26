fun main() {
    println(factorial(5)) // 120
}

/**
 * factorial 꼬리 재귀 함수
 *
 * @param n 팩토리얼을 구할 숫자
 * @param result 팩토리얼 결과
 * @return n! 결과
 * @sample
 * factorial(5) // 120
 */
tailrec fun factorial(n: Int, result: Int = 1): Int {
    if (n == 1) return result
    return factorial(n - 1, result * n)
}

/**
 * BigInteger factorial 꼬리 재귀 함수
 *
 * @param n 팩토리얼을 구할 숫자
 * @param ans 팩토리얼 결과
 * @return n! 결과
 * @sample
 * factorial(BigInteger("5")) // 120
 */
tailrec fun factorial(n: Int, ans: BigInteger = BigInteger("1")): BigInteger {
    if (n == 1) return ans
    return factorial(n - 1, ans.multiply(BigInteger("$n")))
}