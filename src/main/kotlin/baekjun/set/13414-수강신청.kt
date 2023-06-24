val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
// (1 ≤ baekjun.getN ≤ 100,000) , 1 ≤ k ≤ 500,000
fun main() {
    val (n, k) = baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()}
    val map = mutableMapOf<String, Boolean>()
    repeat(k) {
        val id = baekjun.tree.`4803`.br.readLine()
        if(map.getOrDefault(id, true)) { // 이미 학번 있으면
            map -= id
            map += id to true
        }else map += id to true
    }

    bw.write("${map.map{it.key}.take(n).joinToString("\n")}\n")
    bw.flush()
    bw.close()
    baekjun.tree.`4803`.br.close()
}