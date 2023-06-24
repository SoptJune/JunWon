
val br = System.`in`.bufferedReader()
fun main() {
    val (n, l) = baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()}
    val letters = mutableListOf<Pair<Int,Int>>().apply {
        repeat(n) {
            val (a, b) = baekjun.tree.`4803`.br.readLine().split(" ").map{it.toInt()}
            add(a to b)
        }
    }.sortedBy{it.first}
    var restPos = 0 // 사다리가 어디 위치 까지 있는지
    var cnt = 0
    letters.forEach{ 
        
        var (s, e) = it
        var holeLen = e - s // 1, 6 이면 마지막 위치는 포함 X 왜인지는 몰루
        if(restPos > e) return@forEach
        // 만약 rest가 있고, s 보다 resPos가 뒤에 있으면
        if(restPos >= s ) {
            s = restPos
            // println("====================")
            // println("s : $s")
            holeLen = e - s
        }
        // 사다리 길이가 남을 때
        if(holeLen % l != 0) {
            cnt += holeLen / l + 1 
            restPos = s + l * (holeLen/ l + 1)
        }else {
            cnt += holeLen / l
            restPos = s + l  * (holeLen/ l )
        }
        // println("restPos : $restPos")
        // println("holeLen : $holeLen")
        // println("cnt += ${holeLen / l}")
        // println("rest = ${l - holeLen % l}")
    }
    println(cnt)
}