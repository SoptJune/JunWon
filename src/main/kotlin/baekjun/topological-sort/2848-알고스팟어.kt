import java.util.*
val br = System.`in`.bufferedReader()
// a : 97
fun main() {
    // println('z'.code - 'a'.code + 1) // 26
    val n = baekjun.tree.`4803`.br.readLine().toInt()
    val orders = Array<MutableList<Int>>(26){mutableListOf<Int>()}
    val indegree = mutableMapOf<Int, Int>()
    val words = mutableListOf<String>()
    repeat(n) {
        val s = baekjun.tree.`4803`.br.readLine()
        s.forEach { c ->
            indegree[c.code - 97] = 0 // 입력 받은 모든 단어 진입차수 초기화
        }
        words.add(s)
    }
    for (i in 0 until words.size-1){
        for(j in i + 1 until words.size){
        val s1 = words[i] // aa
        val s2 = words[j] // aab
        val l = minOf(s1.length, s2.length)
        var flag = true
        for(k in 0 until l) {
            val c1 = s1[k].code - 97; val c2 = s2[k].code - 97
            if(c1 != c2) {
                flag = false
                orders[c1].add(c2)
                indegree += c2 to (indegree[c2]!! + 1)
                break
            }
        }
        if(flag && s1.length > l) { // s1 = "aaaa" , s2 = "aa"
            println("!")
            return
        }
    }
    }
    // println(indegree)
    val q = LinkedList<Int>().apply {
        indegree.forEach {
            val (k, v) = it
            if(v == 0) add(k)
        } // 나중에 filter로 바꿔보자
    }
    // println(indegree)
    val ans = StringBuilder("")
    var isManyAns = false
    while(q.isNotEmpty()) {
        if(q.size > 1) {
            isManyAns = true
        }
        val now = q.remove()
        ans.append((now + 97).toChar())
        orders[now].forEach { next ->
            indegree[next] = indegree[next]!! - 1
            if(indegree[next] == 0) q.add(next)
        }
    }
    if(ans.length < indegree.size) println("!")
    else if(isManyAns) println("?")
    else println(ans.trim())
}
/** 왜 인접배열 방식으로 풀면 틀리지..?? */

// import java.util.*
// val baekjun.getBr = System.`in`.bufferedReader()
// // a : 97
// fun baekjun.tree.`4803-트리`.main() {
//     // println('z'.code - 'a'.code + 1) // 26
//     val baekjun.getN = baekjun.getBr.readLine().toInt()
//     val orders = Array<BooleanArray>(26){BooleanArray(26)}
//     val indegree = mutableMapOf<Int, Int>()
//     val words = mutableListOf<String>()
//     repeat(baekjun.getN) {
//         val s = baekjun.getBr.readLine()
//         s.forEach { c ->
//             indegree[c.code - 97] = 0 // 입력 받은 모든 단어 진입차수 초기화
//         }
//         words.add(s)
//     }
//     for (i in 0 until words.size-1){
//         for(j in i + 1 until words.size){
//         val s1 = words[i] // aa
//         val s2 = words[j] // aab
//         val l = minOf(s1.length, s2.length)
//         var flag = true
//         for(k in 0 until l) {
//             val c1 = s1[k].code - 97; val c2 = s2[k].code - 97
//             if(c1 != c2) {
//                 flag = false
//                 orders[c1][c2] = true
//                 indegree += c2 to (indegree[c2]!! + 1)
//                 break
//             }
//         }
//         if(flag && s1.length > l) { // s1 = "aaaa" , s2 = "aa"
//             println("!")
//             return
//         }
//     }
//     }
//     // println(indegree)
//     val q = LinkedList<Int>().apply {
//         indegree.forEach {
//             val (k, v) = it
//             if(v == 0) add(k)
//         } // 나중에 filter로 바꿔보자
//     }
//     // println(indegree)
//     val baekjun.getAns = StringBuilder("")
//     var isManyAns = false
//     while(q.isNotEmpty()) {
//         if(q.size > 1) {
//             isManyAns = true
//         }
//         val now = q.remove()
//         baekjun.getAns.append((now + 97).toChar())
//         orders[now].indices.forEach { next ->
//             if(orders[now][next]){
//                 orders[now][next] = false
//                 indegree[next] = indegree[next]!! - 1
//                 if(indegree[next] == 0) q.add(next)
//             }
//         }
//     }
//     if(baekjun.getAns.length < indegree.size) println("!")
//     else if(isManyAns) println("?")
//     else println(baekjun.getAns.trim())
// }