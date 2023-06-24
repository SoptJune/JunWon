package algo

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

// 각 랜선 길이 2^31 -1 이하
// 총 랜선 개수 <= 10_000
// 필요한 랜선 개수 <= 1_000_000 -> Long 가능
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
val arr = LongArray(10_000)
var ans = 0L
fun main() {
    val (k, n) = br.readLine().split(" ").map { it.toInt() }
    repeat(k) { i ->
        arr[i] = br.readLine().toLong()
    }
    val end = arr.maxOrNull() ?: 0
    binarySearch(1, end, n)
    bw.write("$ans")
    bw.flush()
    bw.close()
    br.close()
}

private fun binarySearch(start: Long, end: Long, n: Int) {
    if (end < start) return
    val mid = (start + end) / 2
    val cnt = count(mid)
    if (cnt < n) {
        binarySearch(start, mid - 1, n)
    } else {
        ans = max(ans, mid) // cnt 가 baekjun.getN 이상 이기만 하면 되니까
        binarySearch(mid + 1, end, n)
    }
}

private fun count(x: Long): Long {
    var result = 0L
    arr.forEach { it ->
        result += it / x
    }
    return result
}
