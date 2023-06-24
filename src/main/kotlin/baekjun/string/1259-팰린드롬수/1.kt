import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    while (true) {
        val number = baekjun.tree.`4803`.br.readLine()
        if (number == "0") break
        bw.write(if (isPalindrome(number)) "yes\n" else "no\n")
    }
    bw.flush()
    bw.close()
    baekjun.tree.`4803`.br.close()
}

fun isPalindrome(number: String): Boolean {
    var flag = true
    var s = 0
    var e = number.length - 1
    while (s < e) {
        if (number[s] != number[e]) {
            flag = false
            break
        }
        s++
        e--
    }
    return flag

}