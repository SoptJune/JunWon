package baekjun.hashMap.`10816-숫자 카드 2`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = br.readLine().toInt()
    val numList = br.readLine().split(" ").map { it.toInt() }
    val m = br.readLine().toInt()
    val numHashMap = hashMapOf<Int, Int>()
    val mNumList = br.readLine().split(" ").map { it.toInt() }.onEach { numHashMap[it] = 0 }
    numList.forEach {
        if (numHashMap.containsKey(it)) numHashMap[it] = numHashMap[it]!!.plus(1)
    }
    mNumList.forEach {
        bw.write("${numHashMap[it]} ")
    }
    bw.flush()
    br.close()
    bw.close()
}

