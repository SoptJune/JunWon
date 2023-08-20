package util.rotate_array

fun main() {
    val a = Array(4) { IntArray(6) }
    repeat(4) {
        a[it] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    a.rotateRect().forEach { println(it.toList()) }
}

private fun Array<IntArray>.rotateRect(): Array<IntArray> {
    val n = size
    val m = this[0].size

    return Array(m) { i ->
        IntArray(n) { j ->
            this[n - 1 - j][i]
        }
    }
}

/**
 * input
1 1 1 1 1 1 1
0 0 0 0 0 0 0
0 0 1 0 0 1 0
0 0 0 0 1 0 0


 output
[0, 0, 0, 1]
[0, 0, 0, 1]
[0, 1, 0, 1]
[0, 0, 0, 1]
[1, 0, 0, 1]
[0, 1, 0, 1]
[0, 0, 0, 1]

 * */

