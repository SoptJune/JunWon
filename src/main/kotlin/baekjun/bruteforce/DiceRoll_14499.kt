package baekjun.bruteforce


// https://www.acmicpc.net/problem/14499

private val graph = Array<IntArray>(20) { IntArray(20) }
private val dicex = IntArray(4) // dicex[1] 위, dicex[3] 아래
private val dicey = IntArray(4) // dicey[1] 위, dicey[3] 아래
private var commanders = IntArray(1000)
private val dx = listOf(0, 0, -1, 1) // <-, -> , 위, 아래
private val dy = listOf(1, -1, 0, 0)
private var x = 0
private var y = 0
private var n = 0
private var m = 0
private var ans = mutableListOf<Int>()
fun main() {
    val (_n, _m, _x, _y) = readln().split(" ").map { it.toInt() }
    n = _n
    m = _m
    x = _x
    y = _y
    repeat(n) { i ->
        val t = readln().split(" ").map { it.toInt() }.toIntArray()
        graph[i] = t
    }
    commanders = readln().split(" ").map { it.toInt() }.toIntArray()

    commanders.forEach { rollDice(it) }

    ans.forEach { println(it) }
}

private fun rollDice(c: Int) {
    val cmd = c - 1
    // 범위 체크
    val _x = x + dx[cmd]
    val _y = y + dy[cmd]
    if (((_x in 0 until n) and (_y in 0 until m)).not()) return
    // 주사위 이동
    x = _x
    y = _y
    // 주사위 복사
    val _dicex = dicex.copyOf()
    val _dicey = dicey.copyOf()

    // 주사위 굴리기
    when (cmd) {
        0 -> { // <=
            dicex.shift(-1)
            dicey[1] = dicex[1]
            dicey[3] = dicex[3]
        }

        1 -> { // =>
            dicex.shift(1)
            dicey[1] = dicex[1]
            dicey[3] = dicex[3]
        }

        2 -> { // 위
            dicey.shift(-1)
            dicex[1] = dicey[1]
            dicex[3] = dicey[3]
        }

        3 -> { // 아래
            dicey.shift(+1)
            dicex[1] = dicey[1]
            dicex[3] = dicey[3]
        }
    }
    addNumAndChangeNum()
//    println("dicex : ${dicex.toList()}")
//    println("dicey : ${dicey.toList()}")
//    println()
}

private fun IntArray.shift(amount: Int) {
    val _arr = this.copyOf()
    val provideIdx: (Int) -> Int = { (it + size + amount) % size }
    repeat(size) { i ->
        val idx = provideIdx(i)
        this[idx] = _arr[i]
    }
    return
}

private fun addNumAndChangeNum() {
    ans.add(dicex[1]) // 현재 상단면 ans에 추가
    // 바닥면 바꾸기
    if (graph[x][y] == 0) {// 바닥에 복사
        graph[x][y] = dicex[3]
    } else { // 주사위에 바닥 복사 후 바닥 0
        dicex[3] = graph[x][y]
        dicey[3] = graph[x][y]
        graph[x][y] = 0
    }
}