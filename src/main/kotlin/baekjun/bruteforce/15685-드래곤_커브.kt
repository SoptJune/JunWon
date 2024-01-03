package baekjun.bruteforce


// 0: (→)
// 1: (↑)
// 2: (←)
// 3: (↓)

// 1) 드래곤 커브 그리기
// 끝점을 기준으로 90도 방향으로 회전시켜서 업데이트
// 2) 정사각형 찾기

private val graph = List(101) { BooleanArray(101) }
fun main() {
    val n = readln().toInt() // 커브 개수
    val dx = intArrayOf(0, -1, 0, 1)
    val dy = intArrayOf(1, 0, -1, 0)
    repeat(n) {
        val (y, x, d, g) = readln().split(" ").map { it.toInt() }
        val eV = Vertex(x + dx[d], y + dy[d])
        val sV = Vertex(x, y)
        graph[eV.x][eV.y] = true
        graph[sV.x][sV.y] = true
        dragonCurve(sV, eV, mutableSetOf(sV, eV), 0, g)
    }
    println(countRec())
}

private fun countRec(): Int {
    var cnt = 0
    for (i in 0 until 100) {
        for (j in 0 until 100) {
            if (graph[i][j] && graph[i + 1][j] && graph[i][j + 1] && graph[i + 1][j + 1]) cnt++
        }
    }
    return cnt
}

private fun dragonCurve(start: Vertex, end: Vertex, vertexes: MutableSet<Vertex>, g: Int, limit: Int) {
    if (g == limit) return
    val (ex, ey) = end
    val newVertexes = mutableListOf<Vertex>()
    vertexes.forEach { v ->
        val nv = v.rotate(ex, ey)
        val (nx, ny) = nv
        graph[nx][ny] = true
        newVertexes.add(nv)
    }
    vertexes.addAll(newVertexes)
    // 다음 끝점
    val (sx, sy) = start
    val nEnd = Vertex(sx, sy).rotate(ex, ey)

    dragonCurve(start, nEnd, vertexes, g + 1, limit)
}

private fun Vertex.rotate(ex: Int, ey: Int): Vertex {
    val (x, y) = this
    val (dx, dy) = (x - ex) to (y - ey)
    return Vertex(ex + dy, ey - dx)
}

private data class Vertex(val x: Int, val y: Int)