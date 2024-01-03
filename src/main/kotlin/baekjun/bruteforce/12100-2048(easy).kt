package baekjun.bruteforce


// N : 블록 크기 1 .. 20
// 블록 움직이는 총 경우의 수 4 ** 5 = 1024
// [] : 블록 이동, 합치기
// 1) 블록이 없는 곳까지 이동시킨다
// 2) 블록을 만난다.
// 2 - 1) 숫자가 다르면 정지
// 2 - 2) 이미 합쳐진 블록이면 정지
// 2 - 3) 합친 후 break
// [x] : graph 에서 가장 큰 값 찾기

private var N = 0
private var ans = 0
fun main() {
    N = readln().toInt()
    val blocks = List(N) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    val cop = blocks.map { it.copyOf() }
    move(blocks, 1)
    dfs(cop)
    println(ans)
}
// 10103
//12303
// 0 4 2 2 -> 4 4 0 0 이 되야 하는데 break를 안해서 8 0 0 0 이 되버림..

// traverse
private fun dfs(blocks: List<IntArray>, depth: Int = 0, dirs: String = "") {
    if (depth == 5) {
        ans = maxOf(ans, blocks.max())
        return
    }

    repeat(4) { i ->
        val copyBlocks = blocks.map { it.copyOf() }
        move(copyBlocks, i)
        dfs(copyBlocks, depth + 1, dirs + i)
    }
}
// move

private fun move(blocks: List<IntArray>, dir: Int) {
    val isMerge = Array(N) { BooleanArray(N) }
    when (dir) {
        // 위
        0 -> {
            for (i in 0 until N) {
                for (j in 0 until N) {
                    // 1) block인가?
                    if (blocks[i][j] == 0) continue
                    // move
                    for (cur in i downTo 1) {
                        val next = cur - 1
                        // 2 - 0) next가 0이면 이동
                        if (blocks[next][j] == 0) {
                            blocks[next][j] = blocks[cur][j]
                            blocks[cur][j] = 0
                            continue
                        }
                        // 2 - 1) 숫자가 다르면 정지
                        if (blocks[next][j] != blocks[cur][j]) break
                        // 2 - 2) 이미 합쳐진 블록이면 정지
                        if (isMerge[next][j]) break
                        // 2 - 3) 합치기
                        isMerge[next][j] = true
                        blocks[next][j] *= 2
                        blocks[cur][j] = 0
                        break
                    }
                }
            }
        }
        // 오른쪽
        1 -> {
            for (j in N - 1 downTo 0) {
                for (i in 0 until N) {
                    // 1) block인가?
                    if (blocks[i][j] == 0) continue
                    // move
                    for (cur in j until N - 1) {
                        val next = cur + 1
                        // 2 - 0) 0이면 이동
                        if (blocks[i][next] == 0) {
                            blocks[i][next] = blocks[i][cur]
                            blocks[i][cur] = 0
                            continue
                        }
                        // 2 - 1) 숫자가 다르면 정지
                        if (blocks[i][next] != blocks[i][cur]) break
                        // 2 - 2) 이미 합쳐진 블록이면 정지
                        if (isMerge[i][next]) break
                        // 2 - 3) 합치기
                        isMerge[i][next] = true
                        blocks[i][next] *= 2
                        blocks[i][cur] = 0
                        break
                    }
                }
            }
        }
        // 아래
        2 -> {
            for (i in N - 1 downTo 0) {
                for (j in 0 until N) {
                    // 1) block인가?
                    if (blocks[i][j] == 0) continue
                    // move
                    for (cur in i until N - 1) {
                        val next = cur + 1
                        // 2 - 0) 0이면 이동
                        if (blocks[next][j] == 0) {
                            blocks[next][j] = blocks[cur][j]
                            blocks[cur][j] = 0
                            continue
                        }
                        // 2 - 1) 숫자가 다르면 정지
                        if (blocks[next][j] != blocks[cur][j]) break
                        // 2 - 2) 이미 합쳐진 블록이면 정지
                        if (isMerge[next][j]) break
                        // 2 - 3) 합치기
                        isMerge[next][j] = true
                        blocks[next][j] *= 2
                        blocks[cur][j] = 0
                        break
                    }
                }
            }
        }
        // 왼쪽
        3 -> {
            for (j in 0 until N) {
                for (i in 0 until N) {
                    // 1) block인가 ?
                    if (blocks[i][j] == 0) continue
                    // move
                    for (cur in j downTo 1) {
                        val next = cur - 1
                        // 2 - 0) 0이면 이동
                        if (blocks[i][next] == 0) {
                            blocks[i][next] = blocks[i][cur]
                            blocks[i][cur] = 0
                            continue
                        }
                        // 2 - 1) 숫자가 다르면 정지
                        if (blocks[i][next] != blocks[i][cur]) break
                        // 2 - 2) 이미 합쳐진 블록이면 정지
                        if (isMerge[i][next]) break
                        // 2 - 3) 합치기
                        isMerge[i][next] = true
                        blocks[i][next] *= 2
                        blocks[i][cur] = 0
                        break // <= 이거 때메 안된 거였음
                    }
                }
            }
        }
    }
}

private fun List<IntArray>.max(): Int {
    return flatMap { it.toList() }.max()
}