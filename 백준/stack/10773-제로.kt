import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// O(N)
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main(){

    val stack = mutableListOf<Int>()
    var cnt = 0


    repeat(br.readLine().toInt()) {

        var currentValue = br.readLine().toInt()

        if (currentValue == 0 && stack.isNotEmpty()) {
                cnt -= stack[stack.lastIndex]
                stack.removeLast()
        }else{
            cnt += currentValue
            stack.add(currentValue)
        }
    }
    bw.write("" + cnt + "\n")
    bw.flush()
    bw.close()
    br.close()
}