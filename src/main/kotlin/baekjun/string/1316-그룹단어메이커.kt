import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// https://www.acmicpc.net/problem/1316
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    var cnt = 0
    repeat(baekjun.tree.`4803`.br.readLine().toInt()) {
        val str = baekjun.tree.`4803`.br.readLine()
        val stack = mutableListOf<Char>()
        val visited = BooleanArray(26)
        var isCount = true
        for (chr in str) {
            // 이미 전에 방문한 chr인 경우
            if (visited[chr.code - 97]) {
                isCount = false
                break
            }
            // stack이 비어있다면 추가
            if (stack.isEmpty()){
                stack.add(chr)
            }else{
                if (stack[0] == chr){ // chr와 stack에 담겨있는 문자가 동일할 경우 -> continue
                    continue
                }else{ // chr와 stack에 담겨있는 문자가 다를 경우 -> stack에서 pop하고, pop한 element에 대한 방문처리를 해준 후, chr을 스택에 넣어줌
                    val popElement: Int = stack.removeAt(0).code - 97
                    visited[popElement] = true
                    stack.add(chr)
                }
            }
        }
        if (isCount) cnt++
    }
    bw.write("$cnt\n")
    bw.flush()
    bw.close()
    baekjun.tree.`4803`.br.close()
}
