package baekjun.bruteforce.`2309_일곱난쟁이`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 백트래킹 기법 풀이

var flag = false
fun main() {
    val br= BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val list = mutableListOf<Int>().apply {
    repeat(9)    {
        add(br.readLine().toInt())
        }
    }
    list.sort()
    val visited = mutableListOf<Boolean>().apply {
        repeat(9){
            add(false)
        }
    }
    backTracking(0, 0, list, visited)
   
    for (i in 0 .. 8){
        if(visited[i]) {
            bw.write("" + list[i] + "\n")
        }
    }
    bw.flush()
    bw.close()
    br.close()
}

fun backTracking(sum: Int = 0,
                 num: Int,list: MutableList<Int>,
                 visited: MutableList<Boolean>) {
    if (num == 7){
        if(sum == 100) {flag = true }
        return
    }
    for (i in 0 .. 8){
        if(!visited[i]){
            visited[i] = true
            backTracking(sum+list[i], num+1, list, visited)
            if(flag){return}
            visited[i] = false
        }
    }
}