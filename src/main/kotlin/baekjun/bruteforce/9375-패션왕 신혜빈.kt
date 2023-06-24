import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    repeat(baekjun.tree.`4803`.br.readLine().toInt()) {
        val hashMap = HashMap<String, Int>()
        var keyNumber = 0
        repeat(baekjun.tree.`4803`.br.readLine().toInt()) {
            val (_, key) = baekjun.tree.`4803`.br.readLine().split(" ")
            if (key in hashMap) {
                hashMap[key] = hashMap[key]!!.plus(1)
            } else {
                keyNumber++
                hashMap[key] = 1
            }
        }
        var ans = 1
        hashMap.values.forEach { value ->
            ans *= (value + 1)
        }
        bw.write("${ans - 1}\n")
    }
    bw.flush()
    baekjun.tree.`4803`.br.close()
    bw.close()
}