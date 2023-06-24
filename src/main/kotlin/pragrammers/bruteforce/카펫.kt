package algo

import kotlin.math.sqrt

fun main() {
    println(solution(10, 2).toList())
    println(solution(8, 1).toList())
    println(solution(24, 24).toList())
}

// brown (8 .. 5000) 자연수
// yellow (1 .. 2 * 1e6) 자연수
// 가로 >= 세로
// return : [가로, 세로]
fun solution(brownNum: Int, yellowNum: Int): IntArray {
    val answer: IntArray
    // 가로 >= 세로 이니까 범위를 루트 yellowNum 이하로 줄인다
    repeat(sqrt(x = yellowNum.toDouble()).toInt()) {
        val x = it + 1
        if (yellowNum % x == 0) {
            val y = yellowNum / x
            if (brownNum == sumOfBrownBlock(x, y)) {
                answer = intArrayOf((y + 2), (x + 2))
                return answer
            }
        }
    }
    return intArrayOf()
}

/* yellowBlock으로 BrownBlock수 합 구하기 */
fun sumOfBrownBlock(x: Int, y: Int): Int = (2 * x) + (2 * (y + 2))
