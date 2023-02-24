val br = System.`in`.bufferedReader()
fun main() {

    val g = br.readLine().toInt()
    var s = 1
    var e = 1 
    val ansList = mutableListOf<Int>()

    while (true) {
        if (e * e - s * s >= g) {
            if (e * e - s * s > g && e - s == 1) break
            if (e * e - s * s == g) ansList += e
            s++
        } else {
            e++
        }
    }
    if (ansList.isEmpty()) return println(-1)
    ansList.forEach { println(it) }
}
