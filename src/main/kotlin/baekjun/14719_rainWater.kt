package baekjun

fun solve1() {
    val (_, n) = readln().split(" ").map { it.toInt() }
    val blocks = readln().split(" ").map { it.toInt() }
    var startBlock = blocks[0]
    var startBlockIndex = 0
    var curBlockAmount = 0
    var curLen = 0
    var rainW = 0

    // math
    blocks.forEachIndexed { i, b ->
        // 만약 startBlock 이상이면
        if (startBlock <= b) {
            rainW += curLen * startBlock - curBlockAmount
            // 초기화
            startBlock = b
            startBlockIndex = i
            curLen = 0
            curBlockAmount = 0
        }
        // 만약 curBlock보다 낮으면
        curLen++
        curBlockAmount += b
    }

    // 남은 블럭 처리
    if (blocks.last() < startBlock) {
        var restBlock = blocks.slice(startBlockIndex + 1..blocks.lastIndex)
        val lastBlock = restBlock.last()

        while (restBlock.isNotEmpty() && (restBlock.max() >= lastBlock)
        ) {
            val maxV = restBlock.max()
            val maxIdx = restBlock.indexOf(maxV)
            rainW += (maxIdx + 1) * maxV - restBlock.slice(0..maxIdx).sum()
            // init
            startBlock = maxV
            restBlock = restBlock.slice(maxIdx + 1..restBlock.lastIndex)
        }
    }
    println(rainW)
}

fun solve2() {
    val (_, n) = readln().split(" ").map { it.toInt() }
    val blocks = readln().split(" ").map { it.toInt() }
    var rainwater = 0
    var l = 0
    var r = blocks.lastIndex
    var lMax = 0
    var rMax = 0

    while (l < r) {
        if (blocks[l] <= blocks[r]) { //  l 보다 r의 블록이 더 크면 -> 블록당 최대 lMax만큼의 빗물을 만들 수 있음!
            if (blocks[l] >= lMax) { // l에서 가장 긴 블록보다 현재 l블록이 더 길면
                lMax = blocks[l] // lMax 업데이트
            } else {
                rainwater += lMax - blocks[l] // 빗물 추가!
            }
            l++
        } else {
            if (blocks[r] >= rMax) { //  r 보다 l의 블록이 더 크면 -> 블록당 최대 rMax만큼의 빗물을 만들 수 있음!
                rMax = blocks[r]
            } else {
                rainwater += rMax - blocks[r]
            }
            r--
        }
    }
    println(rainwater)
}

fun main() {
    solve1()
}
