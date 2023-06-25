package baekjun.bruteforce.`2309_일곱난쟁이`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.bitCount
import java.util.StringTokenizer

// 비트마스킹 기법 풀이

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var list: List<Int> = mutableListOf<Int>().apply {
      repeat(9){
          add(br.readLine().toInt())
      }
    }
    list = list.sorted()

    for (i in (1 shl 6).. (1 shl 9)){
        var sum = 0

        if(bitCount(i) == 7){
            for (j in 0 .. 8){
                if((1 shl j and  i) > 0){
                    sum += list[j]
                }
            }
            if(sum == 100) {
                for (j in 0..8) {
                    if ((1 shl j and i) > 0) {
                        bw.write("" + list[j] + "\n")
                    }
                }
                break
            }
        }
    }

    bw.flush()
    bw.close()
    br.close()
}