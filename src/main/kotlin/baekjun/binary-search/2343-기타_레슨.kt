package baekjun.`binary-search`
fun main() {
    val br = System.`in`.bufferedReader()
    val (_, m) = br.readLine().split(" ").map{it.toInt()}
    val videos = br.readLine().split(" ").map{it.toInt()}
    val cal = {mid: Int ->
        var cnt = 1
        var tmp = 0
        videos.forEach { v ->
            if(tmp + v > mid)  {
                tmp = v
                cnt++
            }
            else {
                tmp += v
            }
        }
        cnt
    }
    var s = videos.max()
    var e = 1e9.toInt()
    var mid:Int
    var ans = 1e9.toInt()
    while(s <= e) {
        mid = (s + e) / 2
        if(cal(mid) > m) {
            s = mid + 1
        }else {
            ans = minOf(ans, mid)
            e = mid - 1
        }
    }
    println(ans)
}
// cnt 13
// 1 2 3 4 5 6 7 8 9
