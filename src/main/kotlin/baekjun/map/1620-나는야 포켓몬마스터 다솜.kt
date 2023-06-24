// https://www.acmicpc.net/status?user_id=sis04013&problem_id=1620&from_mine=1

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() {
    val (n, m) = baekjun.tree.`4803`.br.readLine().split(" ").map { it.toInt() }
    val alphaMap = mutableMapOf<String, String>()
    repeat(n) {
        val input = baekjun.tree.`4803`.br.readLine()
        alphaMap += (input to "${it + 1}")
    }
    val alphaList = alphaMap.keys.toList()
    repeat(m) {
        val input = baekjun.tree.`4803`.br.readLine()
        if (input.any { it.isLetter() }) bw.write("${alphaMap[input]}\n")
        else bw.write("${alphaList[input.toInt() - 1]}\n")
    }
    bw.flush()
    bw.close()
    baekjun.tree.`4803`.br.close()
}