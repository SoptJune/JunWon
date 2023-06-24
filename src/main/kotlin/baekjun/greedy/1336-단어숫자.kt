import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

// 모든 문자 대문자임
// 문자를 (0..9) 중 하나로 변환하고 단어들을 더하면됨
// N (1..10)
// 수의 최대길이 8
// 그리디 ?? 브루트포스??
// int : 2 * 10^9
// 일단 수의 범위 10^9 * 10 = 10^10 , 즉 int 범위로 풀어도 되고
// 10! 3 * 10^6 이니까  80 * 10! 해도 약 300만 * 80 == 약 2억 O(2억) 시간제한 2초니까 되려나.. 애매함 코틀린이라 안될수도 있음
// 즉, 그리디 문제 같음.. 시..bal
// 이항계수같은 문제.. 파이썬하고싶네

private const val DEFAULT_FORMAT = "00000000"
private val alphabets = Array<String>(10) { DEFAULT_FORMAT }
val alpMap = mutableMapOf<Char, Int>().apply {
    repeat(26) {
        put((65 + it).toChar(), 0)
    }
}

fun main() {
    val nums = (0..9).toMutableList()
    var ans = 0
    val n = baekjun.tree.`4803`.br.readLine().toInt()
    // 계산 좀 편하게 할려고 이렇게 했는데.. 괜히 이렇게 했네
    repeat(n) {
        val curAlpha = baekjun.tree.`4803`.br.readLine()
        alphabets[it] = DEFAULT_FORMAT.slice(0..7 - curAlpha.length) + curAlpha
    }
//    println(alphabets.toList())
    // 각 알파벳에 상대 수치 부여
    val tmpMap = alpMap.toMutableMap()
    repeat(8) { j ->
        repeat(10) repeat2@{ i ->
            if (alphabets[i][j] == '0') return@repeat2
            val digit = 7 - j
            tmpMap[alphabets[i][j]] = tmpMap[alphabets[i][j]]!! + 10.pow(digit)
        }
    }
//    println(tmpMap)
    // 각 알파벳에 숫자 부여
    tmpMap.keys
        .filter { tmpMap[it] != 0 } // value 값이 0이 아닌 것 keys
        .sortedBy { -tmpMap[it]!! } // value 값을 기준으로 내림차순 정렬
        .forEach { al ->
            if (nums.isEmpty()) return@forEach
            alpMap[al] = nums.removeLast()
        }
//    println(alpMap)
    // 단어 숫자 변환
    alphabets.forEach { str ->
        var cnt = 0
        repeat(8) { i ->
            val digit = 7 - i
            cnt += 0.takeIf { str[i] == '0' } ?: (alpMap[str[i]]!! * 10.pow(digit))
        }
        ans += cnt
    }
    bw.write("$ans")
    bw.flush()
    baekjun.tree.`4803`.br.close()
    bw.close()
}
private fun Int.pow(digit: Int) = this.toFloat().pow(digit).toInt()