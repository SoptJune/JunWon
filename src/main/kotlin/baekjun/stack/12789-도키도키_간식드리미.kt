package baekjun.stack

import java.util.LinkedList
import kotlin.system.exitProcess

fun main() {
    val n = readln().toInt()
    val q = LinkedList<Int>(readln().split(" ").map { it.toInt() - 1 })
    val st = mutableListOf<Int>()

    var curOrder = 0
    while (q.isNotEmpty() && st.isNotEmpty() || curOrder < n){
        val now = q.peek()
        if(now == curOrder){
            q.remove()
            curOrder++
            continue
        }
        val stLast = st.lastOrNull()
        if (now == null){
            if (stLast != curOrder) {
                println("Sad")
                return
            }
        }
        if (stLast == curOrder){
            st.removeLast()
            curOrder++
            continue
        }
        // 둘다 만족 X면
        st.add(q.remove())
    }

    println("Nice")

}