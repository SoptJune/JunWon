package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

private lateinit var str: StringBuilder
private var originalNum: BigInteger = BigInteger("0")
fun main() {
    originalNum = BigInteger(br.readLine())
    str = StringBuilder((originalNum.plus(BigInteger("1"))).toString())
    transformToIntermidiateNum() 
    transformNineToZero()
    bw.write(str.toString())
    bw.flush()
    br.close()
    bw.close()
}

private fun transformToIntermidiateNum() {
    val mid = str.length / 2
    val lSide = str.substring(0 until mid)
    str = StringBuilder(lSide + (if (str.length % 2 == 1) str[mid] else "") + lSide.reversed())
}

private fun transformNineToZero() {
    val mid = str.length / 2
    var l = str.length - 1 - mid
    var r = mid
    if (originalNum >= BigInteger(str.toString())) {
        do {
            if (str[l] == '9') {
                str[l] = '0'
                str[r] = '0'
                l--
                r++
                continue
            }
            str[l] = (str[l].toString().toInt() + 1).toString()[0]
            if (l != r)
                str[r] = (str[r].toString().toInt() + 1).toString()[0]
        } while (originalNum >= BigInteger(str.toString()))
    }
}