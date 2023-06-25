package baekjun.sort.`수 정렬하기2`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
// 계수정렬 : O(N)
fun main() {
    val n = br.readLine().toInt()
    val NumsTable = IntArray(2_000_000 + 1) // 음수와 0까지 고려해야하기 때문에 
    for (i in 0 until n) {
        val currentNum = br.readLine().toInt()
        NumsTable[currentNum + 1_000_000]++
    }
    NumsTable.forEachIndexed { index, i ->
        if (i != 0)
            repeat(i) {
                bw.write("${index - 1_000_000}\n")
            }
    }
    bw.flush()
    bw.close()
    br.close()

}
