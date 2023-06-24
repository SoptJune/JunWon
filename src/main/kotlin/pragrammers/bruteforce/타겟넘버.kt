import java.util.*

fun main() {
    println(solution(intArrayOf(1, 1, 1, 1, 1), 3))
    println(solution(intArrayOf(4, 1, 2, 1), 4))
}

// 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 바꾼다.
// 완탐 : 2 ^ 20 : 약 1_000_000
// numbers element 수: 2 .. 20
// numbers element 범위 : 1.. 50 자연수
// target 범위 : 1 .. 1000
fun solution(numbers: IntArray, target: Int): Int {
    var answer = 0
    val n = numbers.size
    val q = LinkedList<MutableList<Int>>().apply {
        // curIdx, curResult
        push(mutableListOf(0, 0))
    }
    while (!q.isEmpty()) {
        val (curIdx, curResult) = q.pop()
        if (curIdx == n) {
            if (curResult == target) answer++
            continue
        }
        q.push(mutableListOf(curIdx + 1, curResult + numbers[curIdx]))
        q.push(mutableListOf(curIdx + 1, curResult - numbers[curIdx]))
    }
    return answer
}