package programmers.bruteforce
internal class Solution3 {
    // 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.
    // 이 던전들을 최대한 많이 탐험
    // 현재 피로도(k) : k는 1 이상 5,000 이하인 자연수
    // baekjun.getN 1 ~ 8
    // 피로도 1 이상 1,000 이하
    // 최소 피로도 >= 소모 피로도
    var ans = 0
    lateinit var graph: Array<IntArray>
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        graph = dungeons
        dfs(k = k)
        
        return ans
    }
    
    fun dfs(n: Int = 0, k: Int, visited: Int = 0) {
        ans = maxOf(ans, n)
        graph.indices.forEach { i ->
            if((visited and (1 shl i)) == 0 && k >= graph[i][0]){
                dfs(n + 1, k - graph[i][1], visited or (1 shl i))
            }
        }
    }
}