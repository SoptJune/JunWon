package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

// (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
// Long 가능, BigInteger 안써도 됨~
// 필요한 나무의 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
const val MAX_HEIGHT = 2_000_000_000L
fun main() {
    val (_, m) = br.readLine().split(" ").map { it.toLong() }
    val trees = br.readLine().split(" ").map { it.toLong() }
    bw.write("${binarySearch(0, MAX_HEIGHT, m, trees)}\n")
    bw.flush()
    br.close()
    bw.close()
}

private fun binarySearch(start: Long, end: Long, target: Long, trees: List<Long>): Long {
    var ans = 0L
    var s = start
    var e = end
    while (s <= e) {
        val mid = (s + e) / 2
        val treeHeight = getTreeHeight(mid, trees)
        if (target <= treeHeight) {
            ans = max(ans, mid)
            s = mid + 1
        } else {
            e = mid - 1
        }
    }
    return ans
}

private fun getTreeHeight(x: Long, trees: List<Long>): Long {
    var result = 0L
    trees.forEach { height ->
        if (height > x) result += height - x
    }
    Int.MAX_VALUE
    return result
}