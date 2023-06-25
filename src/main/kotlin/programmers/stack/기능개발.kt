package programmers.stack

import kotlin.math.ceil

fun main() {
    println(solution(intArrayOf(95, 90, 99, 99, 80, 99), intArrayOf(1, 1, 1, 1, 1, 1)).toList())
}
fun solution(progresses: IntArray, speeds: IntArray): IntArray {

    val answer = mutableListOf<Int>()
    var curIdx = 0
    // for문을 사용하여 풀이하면 좀 더 깔끔하겠지만 수 있겠지만, while문을 연습하고자 했습니다 ㅎ ㅎ ㅎ
    while (curIdx < progresses.size) {
        var releaseTaskCnt = 1 // 배포할 작업 수
        val curWorkDuration = getWorkDuration(progresses, speeds, curIdx)
        var nextIdx = curIdx + 1
        while (nextIdx < progresses.size ) {
            val nextWorkDuration = getWorkDuration(progresses, speeds, nextIdx)
            // 만약 nextIdx에 위치한 workDuration이 curIdx에 위치한 WorkDuration보다 크다면 break!!
            if (nextWorkDuration > curWorkDuration) { 
                break
            }
            nextIdx++
            releaseTaskCnt++
        }
        curIdx = nextIdx // curIdx를 nextIdx로 점프~ 
        answer.add(releaseTaskCnt) // answer에 배포한 작업 수 저장~
    }
    return answer.toIntArray()
}

private fun getWorkDuration(progresses: IntArray, speeds: IntArray, taskIdx: Int): Int {
    val restTask = 100 - progresses[taskIdx]
    return ceil(restTask / speeds[taskIdx].toFloat()).toInt()
}