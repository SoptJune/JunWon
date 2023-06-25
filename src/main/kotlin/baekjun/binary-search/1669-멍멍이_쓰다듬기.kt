package baekjun.`binary-search`
import kotlin.math.*

private val br = System.`in`.bufferedReader()
fun main() {
    // 2 * 1e9
    val (a, b) = br.readLine().split(" ").map{it.toInt()}
    val target = b - a
    // 2147483647
    // 이분 탐색
    var s = 1
    var e = (2 * (Int.MAX_VALUE.toFloat()).pow(0.5f) - 1).toInt()
    // println(e)
    var mid: Int 
    var n = 1
    while(s <= e) {
        mid = (s + e) / 2
        if(cal(mid, target) < 0) e--
        else { 
            n = mid
            s++
        }
    }
    // println(baekjun.getN)
    val res = target - n*n
    // println(res)
    val ans = (2*n - 1) + (res / n) + if(res % n == 0) 0 else 1
    println(ans)
}

fun cal(cur: Int, target: Int): Int = target - cur*cur