package baekjun.set.`1746-듣보잡`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val (n, m) = br.readLine().split(" ")
    val setA = HashSet<String>()
    val setB = HashSet<String>()
    repeat(n.toInt()) {
        setA.add(br.readLine())
    }
    repeat(m.toInt()) {
        setB.add(br.readLine())
    }
    val setC = setA.intersect(setB).sorted()
    bw.write("${setC.size}\n")
    setC.forEach { bw.write("$it\n") }
    bw.flush()
    bw.close()
    br.close()
}

