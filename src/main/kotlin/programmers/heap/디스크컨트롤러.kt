package programmers.heap

internal class Solution {
    // 작업의 요청부터 종료까지 걸린 시간의 평균
    // jobs의 길이는 1 이상 500 이하
    // 작업이 요청되는 시간은 0 이상 1,000 이하
    // 작업의 소요시간은 1 이상 1,000 이하
    // [작업이 요청되는 시점, 작업의 소요시간]
    // 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
    fun solution(jobs: Array<IntArray>): Int {
    val n = jobs.size
    var ans = 0
    var curIdx = 0
    var waitJobs = jobs.sortedBy { it[0] }
    while (waitJobs.isNotEmpty()) {
        val candidateJobs = waitJobs.filter { it[0] <= curIdx }.sortedBy { it[1] } // O(NlogN)
        val restJobs = waitJobs.filterNot { it[0] <= curIdx } // O(N)
        if (candidateJobs.isEmpty()) {
            curIdx++
            continue
        }
        val curJob = candidateJobs[0]
        curIdx += curJob[1]
        ans += curIdx - curJob[0]
        waitJobs =
            (if (candidateJobs.size > 1) candidateJobs.slice(1..candidateJobs.lastIndex) else emptyList()) + restJobs
    }
    return ans / n
}
}