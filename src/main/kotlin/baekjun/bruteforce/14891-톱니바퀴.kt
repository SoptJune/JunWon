package baekjun.bruteforce

import kotlin.math.pow


// 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
// 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
// 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
// 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점

// 회전 횟수 K(1 ≤ K ≤ 100)
//

// [x] : dir 1일 때 오른쪽 shift, dir -1 일 때 왼쪽 시프트
// [x] : index가 2, 6일 때 검사할 것 (양 옆에 N인지 S인지 판단), N극은 0, S극은 1 에 따라 shift 방향 정하거나 멈추기
// [x] : 점수 계산

private var dirs = IntArray(4)
private lateinit var gears: List<IntArray>
fun main() {
    gears = List(4) {
        readln().map { it.toString().toInt() }.toIntArray()
    }

    repeat(readln().toInt()) {
        val (num, dir) = readln().split(" ").map { it.toInt() }
        // 해당 gear 돌아갈 방향 체크
        dirs.updateDir(num - 1, dir)
//        println(dirs.toList())
        // gear 회전
        repeat(4) { gears[it].shift(dirs[it]) }
//        println(gears.map { it.toList() })
    }
    println(gears.foldIndexed(0) { i: Int, acc: Int, ints: IntArray ->
        if (ints.first() == 0) acc + 0
        else acc + (2.0).pow(i).toInt()
    })
}

fun IntArray.updateDir(idx: Int, dir: Int) {
    dirs[idx] = dir
    // right
    for (curIdx in idx until 3) {
        val curDir = dirs[curIdx]
        if (curDir == 0 || gears[curIdx][2] == gears[curIdx + 1][6]) {
            dirs[curIdx + 1] = 0
            continue
        }
        dirs[curIdx + 1] = curDir * -1
    }
    // left
    for (curIdx in idx downTo 1) {
        val curDir = dirs[curIdx]
        if (curDir == 0 || gears[curIdx][6] == gears[curIdx - 1][2]) {
            dirs[curIdx - 1] = 0
            continue
        }
        dirs[curIdx - 1] = curDir * -1
    }
}

private fun IntArray.shift(dir: Int) {
    if (dir == 1) { // 시계
        val last = last()
        for (i in size - 1 downTo 1) {
            this[i] = this[i - 1]
        }
        this[0] = last
        return
    }
    if (dir == -1) { // 반시계
        val first = this[0]
        for (i in 0 until size - 1) {
            this[i] = this[i + 1]
        }
        this[lastIndex] = first
        return
    }
}