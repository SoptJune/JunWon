package baekjun.bruteforce

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

// N은 50보다 작거나 같은 자연수
// 1) A B 와 친구 -> A,B 는  2-친구사이
// 2) A와 B의 친구 C -> A,B 는 2-친구사이
// 그럼 A <-> B <-> C <-> D A와 D는 친구 사이일까? X , 그저 친구의 친구이면 2-친구인거
//   DisJoint-Set 문제인가?? X O(baekjun.getN^3)
// 그럼 일단 브루트포스로 풀자!
// A-B-C D
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))
private val graph = mutableListOf<MutableList<Int>>().apply {
    repeat(50) {
        add(mutableListOf())
    }
}
private var ans = 0
fun main() {
    val n = br.readLine().toInt()
    repeat(n) { i ->
        br.readLine().toList().forEachIndexed { j, chr ->
            if (chr == 'Y') {
                graph[i].add(j)
            }
        }
    }

    repeat(n) { i ->
        val tmp = mutableSetOf<Int>()
        graph[i].forEach { friend ->
            tmp.add(friend)
            graph[friend].forEach { friendSecond ->
                if (friendSecond != i) tmp.add(friendSecond)
            }
        }
        ans = max(ans, tmp.size)
    }
    bw.write("$ans\n")
    bw.flush()
    br.close()
    bw.close()
}

