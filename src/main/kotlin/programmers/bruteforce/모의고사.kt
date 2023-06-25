package programmers.bruteforce
internal class Solution {
    fun solution(answers: IntArray): List<Int> {
        val f = calScore(answers.toList(), listOf(1, 2, 3, 4, 5))
        val s = calScore(answers.toList(),listOf(2, 1, 2, 3, 2, 4, 2, 5 ))
        val thr = calScore(answers.toList(),listOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5))
        val tmp = listOf(f,s,thr)
        val result = tmp
            .indices
            .filter{ tmp[it] == maxOf(f,s,thr)}
            .map{it + 1}
        return result
    }
    
    fun calScore(ans: List<Int>, cur: List<Int>): Int {
        val ansLen = ans.size
        val curLen = cur.size
        
        val div = ansLen / curLen
        val mod = ansLen % curLen
        var t = 0
        val result = mutableListOf<Int>().apply{
            repeat(div) {
                addAll(cur)
            }
            addAll(cur.slice(0 .. mod-1))
        }
        
        return ans.indices.filter{ ans[it] == result[it]}.size
    }
}