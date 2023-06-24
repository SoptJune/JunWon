import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val (n, m) = baekjun.tree.`4803`.br.readLine().split(" ")
    val setA = HashSet<String>()
    val setB = HashSet<String>()
    repeat(n.toInt()) {
        setA.add(baekjun.tree.`4803`.br.readLine())
    }
    repeat(m.toInt()) {
        setB.add(baekjun.tree.`4803`.br.readLine())
    }
    val setC = setA.intersect(setB).sorted()
    bw.write("${setC.size}\n")
    setC.forEach { bw.write("$it\n") }
    bw.flush()
    bw.close()
    baekjun.tree.`4803`.br.close()
}

