val br = System.`in`.bufferedReader()
var n = 0
lateinit var str: List<String>
var ans = Int.MIN_VALUE
fun main() {
    n = br.readLine().toInt()
    str = br.readLine().toList().map { it.toString() }
    val stack = listOf<String>(str[0])
    bf(1, stack)
    println(ans)
}

private fun bf(idx: Int, stack: List<String>) {
    if (idx == n) {
        // stack 계산
        var cnt = stack[0].toInt()
        var i = 1
        while (i < stack.size) {
            val op = stack[i]
            val curNum = stack[i + 1].toInt()
            cnt = cal(cnt, curNum, op).toInt()
            i += 2
        }
        ans = maxOf(ans, cnt)
        return
    }
    val op = str[idx]
    val curNum = str[idx + 1]

    // 괄호
    val res = cal(stack[stack.size - 1].toInt(), curNum.toInt(), op)
    val newStack = stack.dropLast(1) + res
    var cnt = newStack[0].toInt()
        var i = 1
        while (i < newStack.size) {
            val op = newStack[i]
            val curNum = newStack[i + 1].toInt()
            cnt = cal(cnt, curNum, op).toInt()
            i += 2
        }
    bf(idx + 2, listOf("$cnt"))

    // 괄호 X
    bf(idx + 2, stack + listOf(op, curNum))
}

private fun cal(x: Int, y: Int, op: String): String {
    when (op) {
        "+" -> return "${x + y}"
        "-" -> return "${x - y}"
        "*" -> return "${x * y}"
        else -> return ""
    }
}
