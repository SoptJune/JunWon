//  'A', 'E', 'I', 'O', 'U'
// 모든 경우의 수: 5**5 + 5**4 + 5**3 + 5**2 + 5 = 3905 -> 완전탐색 후 정렬하면 가능
// AAEAE
// AAAAA
// AAEAA
// AAAA
// E
private var vowelSet = mutableSetOf<String>()
private lateinit var vowelTable: List<String>
private val vowels = listOf('A', 'E', 'I', 'O', 'U')

fun solution(word: String): Int {
    initVowelTable()
    return vowelTable.indexOf(word)
}


private fun initVowelTable() {
    dfsVowel()
    vowelTable = vowelSet.sorted()
}

private fun dfsVowel(depth: Int = 0, word: String = "") {
    if (depth == 5) {
        vowelSet.add(word)
        return
    }
    vowels.forEach { chr ->
        dfsVowel(depth + 1, word = word + chr)
    }
    dfsVowel(depth + 1, word = word)
}