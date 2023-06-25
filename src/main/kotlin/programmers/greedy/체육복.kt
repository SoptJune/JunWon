package programmers.greedy
internal class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var ans = 0
        val students = IntArray(n + 2) { // 0 번 31번 index는 indexoutofboundsexception 처리 쉽게 해주려고 추가했습니다
            1
        }
        lost.forEach { studentIdx -> // 체육복 도난 당한 학생은 체육복 -1
            students[studentIdx] -= 1
        }
        reserve.forEach { studentIdx -> // 체육복 챙겨온 학생은 체육복 +1
            students[studentIdx] += 1
        }
        students.forEachIndexed { idx, clothNum ->
            if (clothNum != 2) return@forEachIndexed 
            if (students[idx - 1] == 0) { // idx - 1 번째 학생부터 체육복이 도난 당했는지 확인
                students[idx - 1] += 1
                students[idx] -= 1
                return@forEachIndexed
            }
            if (students[idx + 1] == 0) { // 그 다음 idx + 1 번째 학생부터 체육복이 도난 당했는지 확인
                students[idx + 1] += 1
                students[idx] -= 1
                return@forEachIndexed
            }
        }
        // students 중 체육복이 있는 학생들만 baekjun.getAns++
        students.filterIndexed { idx, _ -> idx in 1..n }.forEach { clothNum -> if (clothNum >= 1) ans++ }
        return ans
    }
}