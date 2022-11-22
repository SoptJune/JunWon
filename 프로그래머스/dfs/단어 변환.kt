fun main() {
    println(solution(begin = "hit", t = "cog", arrayOf("hot", "dot", "dog", "lot", "log", "cog")))
}

// 각 단어는 알파벳 소문자로만 이루어져 있습니다.
// 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
// words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
// begin과 target은 같지 않습니다.
// 변환할 수 없는 경우에는 0를 return 합니다.
private lateinit var visited: MutableList<Boolean>
private lateinit var target: String
private var ans = Int.MAX_VALUE
private lateinit var words: Array<String>
private fun solution(begin: String, t: String, ws: Array<String>): Int {
    target = t
    words = ws
    visited = MutableList(words.size) { false }
    dfs(begin)
    if (ans == Int.MAX_VALUE) return 0
    return ans
}

private fun dfs(curString: String, depths: Int = 0) {
    if (target == curString) {
        ans = depths.coerceAtMost(ans)
        return
    }
    words.forEachIndexed { idx, str ->
        if (!visited[idx] && canChange(from = curString, to = str)) {
            visited[idx] = true
            dfs(str, depths = depths + 1)
            visited[idx] = false
        }
    }
}

/**
 * 한 개의 알파벳만 다른가?*/
private fun canChange(from: String, to: String): Boolean {
    var cnt = 0
    from.forEachIndexed { idx, chr -> if (from[idx] != to[idx]) cnt++ }
    return (cnt == 1)
}
