import java.util.*
val br = System.`in`.bufferedReader()
fun main() {
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        val orders = br.readLine().split(" ").map{it.toInt()}
        val adj = Array<BooleanArray>(n+1){BooleanArray(n+1)}
        val indegree = IntArray(n+1)
        for(i in 0 until n) {
            for(j in i+1 until n) {
                adj[orders[i]][orders[j]] = true // orders[i] -> orders[j]
                indegree[orders[j]]++
            }
        }

        val m = br.readLine().toInt()
        // 1) 진입차수 다시 초기화
        repeat(m) { // 순위
            val (a , b) = br.readLine().split(" ").map{it.toInt()}
            if(adj[a][b]){ // 작년에 a -> b였던 경우
                adj[a][b] = false
                indegree[b]--
                adj[b][a] = true
                indegree[a]++
            }else{ // 작년에 b -> a였던 경우
            adj[b][a] = false
            indegree[a]--
            adj[a][b] = true 
            indegree[b]++
            }
        }
        // 2) 위상 정렬
        
        // 2 - 1 진입차수 0
        val q = LinkedList<Int>().apply{ // 현재 노드, 진입 차수
            for (i in 1 .. n) {
                if(indegree[i] == 0) add(i)
            }
        } 
        
        // 2 - 2 위상 정렬
        val ans = mutableListOf<Int>()
     
        var isManyAns = false
        while(q.isNotEmpty()){
            if(q.size > 1) { // 만약 진입 차수가 0인 녀석이 한 개가 아닌 때 : "?"
                isManyAns = true
                break
            }
            val cur = q.remove()
            ans.add(cur)
            adj[cur].indices.forEach { next ->
                if(adj[cur][next]) {
                    indegree[next]--
                    if(indegree[next] == 0) q.add(next)
                }   
            }
        }
        if(isManyAns) println("?")
        else if(ans.size < n) println("IMPOSSIBLE")  // cycle
        else println(ans.joinToString(" "))
    }
}