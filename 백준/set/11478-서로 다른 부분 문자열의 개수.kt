fun main() {
    val s = readln()
    val n = s.length
    val set = mutableSetOf<String>()
    for (i in 1..n) { // 길이
        for (j in 0..n - i) { // index
            set.add(s.slice(j until j + i))
        }
    }
    println(set.size)
}