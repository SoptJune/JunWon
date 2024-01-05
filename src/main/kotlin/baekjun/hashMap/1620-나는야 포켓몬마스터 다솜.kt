package baekjun.hashMap
// https://www.acmicpc.net/status?user_id=sis04013&problem_id=1620&from_mine=1

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val alphaMap = mutableMapOf<String, String>()
    repeat(n) {
        val input = br.readLine()
        alphaMap += (input to "${it + 1}")
    }
    val alphaList = alphaMap.keys.toList()
    repeat(m) {
        val input = br.readLine()
        if (input.any { it.isLetter() }) bw.write("${alphaMap[input]}\n")
        else bw.write("${alphaList[input.toInt() - 1]}\n")
    }
    bw.flush()
    bw.close()
    br.close()
}