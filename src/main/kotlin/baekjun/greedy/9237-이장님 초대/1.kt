import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = baekjun.tree.`4803`.br.readLine().toInt()
    val trees = baekjun.tree.`4803`.br.readLine()
        .split(" ")
        .map { it.toInt() }
        .sortedDescending() // 내림차순으로 정렬
    var ans = -1 // 초깃값을 그냥 -1로 세팅했습니다 제 취향..

    trees.forEachIndexed { idx, tree ->
        val tmp = tree + idx + 1 // 해당 나무 자라는 시간(tree) + 이전 나무들 심는데 걸린 시간(idx) + 나무 심는데 걸리는 시간(1)
        ans = max(tmp, ans)
    }
    bw.write("${++ans}\n") // 이장님은 나무 다 심은 뒤 하루 뒤 오시니까 +1 ㅎ ㅎ ㅎ
    bw.flush()
    bw.close()
    baekjun.tree.`4803`.br.close()
}