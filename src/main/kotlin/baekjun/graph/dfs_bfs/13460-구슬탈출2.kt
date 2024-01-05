package baekjun.graph.dfs_bfs

import java.util.*
import kotlin.properties.Delegates

// '.', '#', 'O', 'R', 'B'
private val dx = intArrayOf(-1, 0, 1, 0) //  위, 오, 아래, 왼
private val dy = intArrayOf(0, 1, 0, -1)
private const val W = "#"
private const val E = "."
private const val H = "O"
private const val R = "R"
private const val B = "B"
private var n by Delegates.notNull<Int>()
private var m by Delegates.notNull<Int>()
private lateinit var graph: Array<Array<String>>
private val visited = Array(10) { Array(10) { Array(10) { BooleanArray(10) } } }
private lateinit var hv: Vertex
var isInRedHole = false
var isInBlueHole = false
// r, b : 방향에 더 가까운 쪽이 먼저 이동
// VISITED는 R만 고려


fun main() {
    val r = readln().split(" ").map { it.toInt() }
    n = r[0]; m = r[1]
    var frv = Vertex(0, 0)
    var fbv = Vertex(0, 0)
    graph = Array(n) {
        readln().toList().map { it.toString() }.toTypedArray().apply {
            if (contains(R)) {
                frv = Vertex(it, indexOf(R))
                set(indexOf(R), E)
            }
            if (contains(B)) {
                fbv = Vertex(it, indexOf(B))
                set(indexOf(B), E)
            }

            if (contains(H)) {
                hv = Vertex(it, indexOf(H))
            }
        }
    }

    val q = LinkedList<Trial>().apply { add(Trial(0, "", frv, fbv)) }
    while (q.isNotEmpty()) {
        val (a, path, r, b) = q.remove()
        visited[r.x][r.y][b.x][b.y] = true
        // a가 10번이면 break
        if (a == 10) break
        // 공 굴리기
        repeat(4) { dir ->
            isInRedHole = false
            isInBlueHole = false
            val (rv, bv) = when (dir) {
                0 -> move(r, b, 0, r.x < b.x)
                1 -> move(r, b, 1, r.y > b.y)
                2 -> move(r, b, 2, r.x > b.x)
                else -> move(r, b, 3, r.y < b.y)
            }
            if (isInBlueHole) return@repeat
            if (isInRedHole) {
                println(a + 1)
                return
            }
            if (visited[rv.x][rv.y][bv.x][bv.y]) return@repeat
            q.add(Trial(a + 1, path + dir, rv, bv))
        }
    }
    println(-1)
}

private fun move(r: Vertex, b: Vertex, dir: Int, flag: Boolean): Pair<Vertex, Vertex> {
    return if (flag) {
        val rv = moveRed(r.x, r.y, dir, b)
        rv to moveBlue(b.x, b.y, dir, rv)
    } else {// b이 먼저 이동
        val bv = moveBlue(b.x, b.y, dir, r)
        moveRed(r.x, r.y, dir, bv) to bv
    }
}

private fun moveRed(x: Int, y: Int, dir: Int, bv: Vertex): Vertex {
    var cx = x
    var cy = y
    while (true) {
        val nx = cx + dx[dir]
        val ny = cy + dy[dir]

        // 벽 만나면 o
        if (graph[nx][ny] == W) {
            return Vertex(cx, cy)
        }
        //  Blue가 hole 안에 없고, Bule 만났을 때
        if (Vertex(nx, ny) == bv && isInBlueHole.not()) return Vertex(cx, cy)

        // 1칸 이동
        cx = nx
        cy = ny

        // hole 인지 체크
        if (hv == Vertex(cx, cy)) {
            isInRedHole = true
            return Vertex(cx, cy)
        }
    }
}

private fun moveBlue(x: Int, y: Int, dir: Int, rv: Vertex): Vertex {
    var cx = x
    var cy = y
    while (true) {
        val nx = cx + dx[dir]
        val ny = cy + dy[dir]

        //  벽 만나면 Stop
        if (graph[nx][ny] == W) {
            return Vertex(cx, cy)
        }

        //  Red가 hole안에 없고, Red 만났을 때
        if (isInRedHole.not() && rv == Vertex(nx, ny)) return Vertex(cx, cy)
        // 1칸 이동
        cx = nx
        cy = ny

        // hole 인지 체크
        if (hv == Vertex(cx, cy)) {
            isInBlueHole = true
            return Vertex(cx, cy)
        }
    }
}

private data class Trial(val amount: Int, val path: String, val r: Vertex, val b: Vertex)
private data class Vertex(val x: Int, val y: Int)

/*
10 10
##########
#RB....#.#
#..#.....#
#........#
#.O......#
#...#....#
#........#
#........#
#.......##
##########

ans : 10
*/