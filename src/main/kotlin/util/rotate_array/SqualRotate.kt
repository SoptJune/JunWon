package util.rotate_array

/**
 * 정사각형 배열 시계방향으로 회전!
 *
 * 새끼문제로 자주 나오는 유형
 * 매우 헷갈리고 시간 많이 잡아먹으니까 틈틈히 연습
 * */
private fun rotate(a: Array<IntArray>): Array<IntArray> {
    val n = a.size
    var s = 0
    repeat(n / 2) {
        val e = n - s - 1
        val tmp = a[s].copyOf() // 첫 번째 행 복사

        for (i in s + 1..e) {
            a[s][s + e - i] = a[i][s]
        }

        for (i in s + 1..e) {
            a[i][s] = a[e][i]
        }
        for (i in s..e - 1) {
            a[e][s + e - i] = a[i][e]
        }

        for (i in s..e - 1) {
            a[i][e] = tmp[i]
        }
        s++
    }
    return a
}

private fun rotate2(a: Array<IntArray>): Array<IntArray> {
    return Array(5) { i ->
        IntArray(5) { j ->
            a[4 - j][i]
        }
    }
}

fun main() {
    val a = Array(5) { IntArray(5) }
    repeat(5) {
        a[it] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    rotate(a).forEach { println(it.toList()) }
}