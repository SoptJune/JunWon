package baekjun.`two-pointer`

import kotlin.math.abs

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()

// 10C2 는 시간초과
// i, j 투 포인터 알고리즘
// if arr[i]+ arr[j] > 0 -> i--
// else j--
fun main() {
    with(br) {
        val n = readLine().toInt()
        val a = readLine().split(" ").map { it.toInt() }
        var i = 0
        var j = n - 1
        var sum = 1e9.toInt() * 2 + 1
        var ans = arrayOf(0, n - 1)
        while (i < j) {
            val s = (a[i] + a[j]).apply {
                if (abs(this) < abs(sum)) {
                    sum = this
                    ans = arrayOf(a[i], a[j])
                }
            }
            if (s > 0) j--
            else i++
        }
        bw.write(ans.joinToString(" "))
        bw.flush()
        bw.close()
    }
}
