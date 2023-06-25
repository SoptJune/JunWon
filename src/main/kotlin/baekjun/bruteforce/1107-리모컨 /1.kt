package baekjun.bruteforce.`1107-리모컨 `

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.min

// N (0 ≤ N ≤ 500,000)이 주어진다.
// 고장난 버튼의 개수 M (0 ≤ M ≤ 10)
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

private var targetChannelNum = 0
private const val START_NUM = 100
private const val Max_NUM = 1_000_000
private val buttonMap: HashMap<Int, Boolean> by lazy {
    hashMapOf<Int, Boolean>().apply {
        repeat(10) {
            put(it, false)
        }
    }
}

fun main() {
    targetChannelNum = br.readLine().toInt()
    var ans = abs(targetChannelNum - START_NUM) // 초기값: + or -버튼만 눌러서 이동한 값
    if (br.readLine().toInt() != 0) {
        br.readLine()
            .split(" ")
            .map { it.toInt() }
            .onEach { buttonNum ->
                buttonMap[buttonNum] = true
            }
    }
    repeat(Max_NUM) { num ->
        val channelNum = num.toString() // "123"
        channelNum.forEachIndexed { idx, chr ->
            val candidateBrokenBtn = chr.toString().toInt() // 1
            if ((buttonMap[candidateBrokenBtn]!!)) return@repeat // 만약 누를 수 없으면
            if (idx == channelNum.lastIndex) {
                ans = min(ans, channelNum.length + abs(targetChannelNum - channelNum.toInt()))
            }
        }
    }
    // O(6000만) -> 가능
    bw.write("$ans\n")
    bw.flush()
    br.close()
    bw.close()
}