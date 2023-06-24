val br = System.`in`.bufferedReader()
fun main() {
    val n = baekjun.tree.`4803`.br.readLine().toInt()
    val nums = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    if (n <= 2) return println(0)
    val map = nums.groupingBy { it }.eachCount().toMutableMap() // 추후에 조절
    var ans = 0
    for (i in 0..n - 2) {
        for (j in i + 1..n - 1) {
            val sum = nums[i] + nums[j]
            if (map.getOrDefault(sum, -1) != -1) {
                // (0 , 2), map[2] = 1
                if (map[sum] == 1 && sum == nums[i]) continue
                if (map[sum] == 1 && sum == nums[j]) continue
                // (0, 0) map[0] = 2
                if (nums[i] == 0 && nums[j] == 0 && map[sum] == 2) continue
                ans += map.getOrDefault(sum, 0)
                map += sum to -1
            }
        }
    }
    println(ans)
}
