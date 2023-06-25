package baekjun.string.`1259-팰린드롬수`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    while (true) {
        val number = br.readLine()
        if (number == "0") break
        bw.write(if (isPalindrome(number)) "yes\n" else "no\n")
    }
    bw.flush()
    bw.close()
    br.close()
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