package programmers.bruteforce
import kotlin.math.*
internal class Solution2 {
    // 999 999 9
    // size 10_000_000
    val isPrime = BooleanArray(10_000_000){true}.apply{
        this[0] = false
        this[1] = false
    }
    fun solution(numbers: String): Int {
        initIsPrime()
        var ans = mutableSetOf<Int>()
        // println(isPrime.take(100))
        // println(listOf('1','2').perm(2).first().joinToString("").toInt())
        repeat(numbers.length) {
            val r = it + 1
            numbers.toList().perm(r).forEach { num ->
                val res = num.joinToString("").toInt()
                if(isPrime[res]) ans += res
            }
        }
        return ans.size
    }
    
    fun List<Char>.perm(r: Int, cur:List<Char> = emptyList()): List<List<Char>> {
        if(r == 0) return listOf(cur)
        return flatMap{ (this - it).perm(r-1, cur + it) }
    }
    
    fun initIsPrime() {
        for (i in 2 .. (10_000_000.0 - 1).pow(0.5).toInt()){
            if(!isPrime[i]) continue
            for (j in i + i .. 10_000_000 - 1 step(i)) {
                isPrime[j] = false
            }
        }
    }
}