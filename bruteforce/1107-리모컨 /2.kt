package org.techtown.practice_mvvm_event.algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.min

// N (0 ≤ N ≤ 500,000)이 주어진다.
// 고장난 버튼의 개수 M (0 ≤ M ≤ 10)
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

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
    repeat(Max_NUM) { channelNums ->
        val num = channelNums.toString() // "123"
        num.forEachIndexed { idx, chr ->
            val channelNum = chr.toString().toInt() // 1
            if ((channelNum in brokenButton)) return@repeat // 만약 누를 수 없으면
            if (idx == num.lastIndex) {
                ans = min(ans, num.length + abs(targetChannelNum - num.toInt()))
            }
        }
    }
    // O(6000만) -> 가능
    bw.write("$ans\n")
    bw.flush()
    br.close()
    bw.close()
}