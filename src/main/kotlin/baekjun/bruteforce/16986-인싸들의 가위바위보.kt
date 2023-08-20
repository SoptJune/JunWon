package baekjun.bruteforce

import kotlin.system.exitProcess

private val players = Array(3) { IntArray(20) }
private var winCnts = IntArray(3)
private var pointers = IntArray(3)
private lateinit var graph: Array<IntArray>
private var n = 0
private var k = 0
fun main() {
    val (_n, _k) = readln().split(" ").map { it.toInt() }
    n = _n; k = _k
    graph = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    players[1] = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
    players[2] = readln().split(" ").map { it.toInt() - 1 }.toIntArray()

    dfs()
    println(0)
}

private fun dfs(depth: Int = 0, visited: Int = 0) {
    if (depth == n) {
        play(0, 1)
        // 초기화
        pointers = IntArray(3)
        winCnts = IntArray(3)
        return
    }

    repeat(n) { i ->
        if (visited and 1.shl(i) == 0) {
            players[0][depth] = i
            dfs(depth + 1, visited or 1.shl(i))
        }
    }
}

private fun play(
    a: Int,
    b: Int
) {
    if (isFinished()) return
    val aAction = players[a][pointers[a]]
    val bAction = players[b][pointers[b]]
    pointers[a]++
    pointers[b]++

    val res = graph[aAction][bAction]
    // 비김
    if (res == 1) {
        val winner = maxOf(a, b)
        winCnts[winner]++
        return play(winner, 3 - (a + b))
    }

    // a가 이김
    if (res == 2) {
        winCnts[a]++
        return play(a, 3 - (a + b))
    }
    // b가 이김
    winCnts[b]++
    play(b, 3 - (a + b))
}

private fun isFinished(): Boolean {
    if (winCnts[0] == k) {
        println(1)
        exitProcess(0)
    }
    if(pointers[0] == n) return true
    return winCnts.any { it == k }
}