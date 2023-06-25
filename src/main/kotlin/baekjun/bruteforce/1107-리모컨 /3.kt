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
private var brokenButton: List<Int> = emptyList()
fun main() {
    targetChannelNum = br.readLine().toInt()
    var ans = abs(targetChannelNum - START_NUM) // 초기값: + or -버튼만 눌러서 이동한 값
    if (br.readLine().toInt() != 0) {
        brokenButton = br.readLine().split(" ").map { it.toInt() }
    }
    for (num in 0..Max_NUM) { // O(100만)
        val channelNum = num.toString()
        var hasBrokenBtn = false
        for (chr in channelNum) { // O(6)
            val candidateBrokenBtn = chr.toString().toInt()
            if ((candidateBrokenBtn in brokenButton)) { // O(10)
                hasBrokenBtn = true
                break // 만약 번호로 눌러서 채널로 이동가능 불가능
            }
        }
        // 만약 번호로 눌러서 채널로 이동가능하다면
        if (!hasBrokenBtn) ans = min(ans, channelNum.length + abs(targetChannelNum - num))
    }
    // O(6000만) -> 가능
    bw.write("$ans\n")
    bw.flush()
    br.close()
    bw.close()
}