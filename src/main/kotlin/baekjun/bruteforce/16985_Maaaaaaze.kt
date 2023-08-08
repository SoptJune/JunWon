package baekjun.bruteforce

import java.util.LinkedList


private val dz = listOf(1, -1, 0, 0, 0, 0)
private val dx = listOf(0, 0, 1, -1, 0, 0)
private val dy = listOf(0, 0, 0, 0, 1, -1)

private var ans = Int.MAX_VALUE
private var bd = Array(5) { Array(5) { IntArray(5) } }
private val ck = BooleanArray(5)
fun main() {
    // z, x, y
    val board = Array(5) { Array(5) { IntArray(5) } }
    repeat(5) { z ->
        repeat(5) { x ->
            board[z][x] = readln().split(" ").map { it.toInt() }.toIntArray()
        }
    }
    // 5! 순열
    dfs(board)
    println(if (ans == Int.MAX_VALUE) -1 else ans)
}

// 총 시간 복잡도 O(120 * 1000 * 1000) = 1e8 약 1억... 약 1.xx초
// step 1 : permutation - O(5!) = O(120)
fun dfs(board: Array<Array<IntArray>>, depth: Int = 0) {
    if (depth == 5) {
        rotateBoard()
        return
    }

    board.forEachIndexed { i, arr ->
        if (ck[i].not()) {
            ck[i] = true
            bd[depth] = arr
            dfs(board, depth + 1)
            ck[i] = false
        }
    }
}

// step 2 : rotate - O(4 ^ 5) = O(1000)
fun rotateBoard(depth: Int = 0) {
    if (depth == 5) {
        // 도착 가능?
        if (bd[0][0][0] == 1) bfs()
        return
    }
    repeat(4) {
        bd[depth] = rotate(bd[depth])
        // 출발 가능?
        if (bd[0][0][0] == 1) rotateBoard(depth + 1)
    }
}

// step 3 : 길찾기 - O(V + E) = O(125 + 6 * 125) = O(1000)

fun bfs() {
    val ck2 = Array(5) { Array(5) { IntArray(5) { -1 } } }

    val q = LinkedList<Triple<Int, Int, Int>>().apply {
        add(Triple(0, 0, 0))
        ck2[0][0][0] = 0
    }
    while (q.isNotEmpty()) {
        val now = q.pop()
        if (now == Triple(4, 4, 4)) {
            ans = minOf(ans, ck2[4][4][4])
            break
        }
        val _z = now.first
        val _x = now.second
        val _y = now.third
        // 길 찾기
        repeat(6) { i ->
            val z = _z + dz[i]
            val x = _x + dx[i]
            val y = _y + dy[i]

            if (x in 0..4 && y in 0..4 && z in 0..4) {
                if (bd[z][x][y] == 1 && ck2[z][x][y] == -1) { // 방문 X
                    ck2[z][x][y] = ck2[_z][_x][_y] + 1
                    q.add(Triple(z, x, y))
                }
            }
        }
    }
}

// 정사각형 시계 방향 rotate
private fun rotate(a: Array<IntArray>): Array<IntArray> {
    return Array(5) { i ->
        IntArray(5) { j ->
            a[4 - j][i]
        }
    }
}