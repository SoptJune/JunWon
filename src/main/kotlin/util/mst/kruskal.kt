package util.mst

private val parents = IntArray(1001) { -1 } // Node의 개수로 초기화

private fun findParent(curNode: Int): Int {
    if (parents[curNode] <= -1) return curNode

    parents[curNode] = findParent(parents[curNode])
    return parents[curNode]
}

private fun union(x: Int, y: Int): Boolean {
    val parentX = findParent(x)
    val parentY = findParent(y)

    if (parentX == parentY) return false

    if (parents[parentX] >= parents[parentY]) {
        parents[parentY] += parents[parentX]
        parents[parentX] = parentY
    } else {
        parents[parentX] += parents[parentY]
        parents[parentY] = parentX
    }
    return true
}
