package baekjun.hashMap.`1920-수 찾기`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = br.readLine().toInt()
    val numHashMap = hashMapOf<Int, Int>()
    val numList = br.readLine().split(" ").map { it.toInt() }
    val m = br.readLine().toInt()
    val mNumList = br.readLine().split(" ").map { it.toInt() }

    mNumList.forEach { numHashMap[it] = 0 }
    numList.forEach { numHashMap[it] = 1 }
    mNumList.forEach { bw.write("${numHashMap[it]}\n") }
    bw.flush()
    br.close()
    bw.close()
}

