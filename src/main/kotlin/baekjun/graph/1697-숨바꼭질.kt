package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

// 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)
private val visited = IntArray(100_001) { -1 }
fun main() {
    val (s, e) = br.readLine().split(" ").map { it.toInt() }
    visited[s] = 0
    val queue: Queue<Int> = LinkedList<Int>().apply { push(s) }
    while (queue.isNotEmpty()) {
        val curNode = queue.poll()
        if (curNode == e) {
            bw.write("${visited[curNode]}")
            bw.flush()
            bw.close()
            br.close()
            return
        }
        listOf(2 * curNode, curNode - 1, curNode + 1)
            .filter { nextNode -> nextNode in 0..100_000 && visited[nextNode] == -1 }
            .forEach { nextNode ->
                visited[nextNode] = visited[curNode] + 1
                queue.add(nextNode)
            }
    }
}