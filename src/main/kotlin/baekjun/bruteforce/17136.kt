package baekjun.bruteforce

/**
- 10 x 10 Board 가 있음
- 색종이는 1x1 ~ 5x5 로 5종류가 있다
- 색종이는 각가 5개씩 있음
- 1이 적힌 칸은 모두 색종이로 덮여져야 한다
- 0이 적힌 칸에는 색종이가 있으면 안 된다.

1을 모두 덮는 색종이의 최소 개수 출력
불가능할 경우 -1

N 100

 */

private var bd = Array(10) { IntArray(10) }
private var rest = IntArray(5) { 5 }
private var ans = Int.MAX_VALUE
fun main() {
    repeat(10) {
        bd[it] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    back(0, 0)

    if (ans == Int.MAX_VALUE) return println(-1)
    println(ans)
}

private fun back(x: Int, y: Int) {
    if (x == 10) {
        if (possible()) ans = minOf(ans, 25 - rest.sum())
        return
    }

    // 0이면 다음으로 바로 가
    if (bd[x][y] == 0) {
        val (nx, ny) = nextNode(x, y)
        back(nx, ny)
        return
    }

    repeat(5) {
        val size = it + 1
        // 1일 때
        if (canPut(x, y, size)) {
            rest[size - 1] -= 1
            put(x, y, size)
            val (nx, ny) = nextNode(x, y)
            // go next
            back(nx, ny)
            // finish
            rollback(x, y, size)
            rest[size - 1] += 1
        }
    }
}

private fun nextNode(x: Int, y: Int): Pair<Int, Int> {
    if (y == 9) return x + 1 to 0
    return x to y + 1
}

private fun canPut(x: Int, y: Int, size: Int): Boolean {
    // 판 안에 없으면 false
    if (x + size > 10) return false
    if (y + size > 10) return false
    // 잔여 색종이 없으면 false
    if (rest[size - 1] == 0) return false
    // 순회
    for (i in x until x + size) {
        for (j in y until y + size) {
            // 0 이 있으면 false 반환
            if (bd[i][j] == 0) return false
        }
    }
    return true
}

private fun put(x: Int, y: Int, size: Int) {
    for (i in x until x + size) {
        for (j in y until y + size) {
            bd[i][j] = 0
        }
    }
}

private fun rollback(x: Int, y: Int, size: Int) {
    for (i in x until x + size) {
        for (j in y until y + size) {
            bd[i][j] = 1
        }
    }
}

private fun possible(): Boolean {
    for (i in 0..9) {
        for (j in 0..9) {
            if (bd[i][j] == 1) {
                return false
            }
        }
    }
    return true
}

