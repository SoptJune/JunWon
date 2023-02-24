val br = System.`in`.bufferedReader()
fun main() {
    val n = br.readLine().toInt()
    val nums = br.readLine().split(" ").map { it.toInt() }.sorted()
    var ans = 0
    nums.indices.forEach {
        val idx = it
        val target = nums[idx]
        var s = 0
        var e = nums.size - 1

        while (s < e) {
            if (nums[s] + nums[e] > target) {
                e--
            } else if (nums[s] + nums[e] < target) {
                s++
            } else if (s != idx && e != idx) {
                ans++
                break
            }
            if (s == idx) s++
            if (e == idx) e--
        }
    }
    println(ans)
}
