package hous.release.android

fun main() {
    println(
        solution(
            arrayOf(
                arrayOf("ICN", "BOO"),
                arrayOf("ICN", "COO"),
                arrayOf("COO", "DOO"),
                arrayOf("DOO", "COO"),
                arrayOf("BOO", "DOO"),
                arrayOf("DOO", "BOO"),
                arrayOf("BOO", "ICN"),
                arrayOf("COO", "BOO")
            )
        ).toList()
    )
}

// 항상 "ICN" 공항에서 출발합니다.
// 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
// 주어진 공항 수는 3개 이상 10,000개 이하입니다.
// tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
// 주어진 항공권은 모두 사용해야 합니다.
// 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
// 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.

// 문제의 조건을 마음대로 생각하고.. 중복되는 경로를 체크하지 않아서 계속 실패했었네요..
private lateinit var routeMap: MutableMap<String, MutableList<String>>
private lateinit var routeVisited: MutableMap<String, MutableMap<String, Int>>
private lateinit var answer: MutableList<String>
private var ticketCnt = 0
private fun solution(tickets: Array<Array<String>>): Array<String> {
    ticketCnt = tickets.size
    routeMap = mutableMapOf()
    routeVisited = mutableMapOf()
    answer = mutableListOf("ICN")
    // 초기화 작업
    tickets.forEach { route ->
        routeMap[route[0]] = mutableListOf()
        routeVisited[route[0]] = mutableMapOf()
    }
    tickets.forEach { route ->
        routeMap[route[0]]!!.add(route[1])
        routeVisited[route[0]]!![route[1]] = 1.takeIf { routeVisited[route[0]]!![route[1]] == null }
            ?: (routeVisited[route[0]]!![route[1]]!! + 1)
    }
    routeMap.keys.forEach { airport ->
        routeMap[airport] = routeMap[airport]!!.sorted().toMutableList()
    }
    println(routeMap)
    dfs(now = "ICN")
    return answer.toTypedArray()
}

private fun dfs(now: String) {
    routeMap[now]?.forEach { next ->
        if (routeVisited[now] != null && routeVisited[now]!![next]!! != 0) {
            routeVisited[now]!![next] = routeVisited[now]!![next]!! - 1 // 방문 가능 횟수 - 1
            answer.add(
                next
            )
            dfs(next)
            // 아래 조건식을 만족하면 정답을 찾은 거!!
            if (answer.size == ticketCnt + 1) return
            routeVisited[now]!![next] = routeVisited[now]!![next]!! + 1 // 방문 가능 횟수 + 1
            answer.removeLast()
        }
    }
}
