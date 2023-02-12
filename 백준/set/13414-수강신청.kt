val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
// (1 ≤ n ≤ 100,000) , 1 ≤ k ≤ 500,000
fun main() {
    val (n, k) = br.readLine().split(" ").map{it.toInt()}
    val map = mutableMapOf<String, Boolean>()
    repeat(k) {
        val id = br.readLine()
        if(map.getOrDefault(id, true)) { // 이미 학번 있으면
            map -= id
            map += id to true
        }else map += id to true
    }

    bw.write("${map.map{it.key}.take(n).joinToString("\n")}\n")
    bw.flush()
    bw.close()
    br.close()
}