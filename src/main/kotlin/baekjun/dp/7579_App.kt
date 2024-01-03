package baekjun.dp

import kotlin.math.pow

object `7579_App` {

    @JvmStatic
    fun main(args: Array<String>) {
        knapsack2()
    }

    // cost를 target으로 한 0-1냅색 풀이
    private fun knapsack2() {
        val (n, M) = readln().split(" ").map { it.toInt() }
        // 사용 중인 메모리의 바이트 수인 m1, ..., mN
        val memories = readln().split(" ").map { it.toInt() }
        // 각 앱을 비활성화 했을 경우의 비용 c1, ..., cN
        val costs = readln().split(" ").map { it.toInt() }

        // d[x] : x 만큼의 cost로 확보할 수 있는 최대 메모리
        val d = IntArray(costs.sum() + 1)
            .apply { this[lastIndex] = memories.sum() }

        for (i in 0 until n) {
            for (j in costs.sum() - 1 downTo 0) {
                val cost = costs[i]
                val memory = memories[i]
                if (j >= cost) {
                    d[j] = maxOf(d[j], d[j - cost] + memory)
                }
            }
        }
        println(d.toList())
        println(d.indexOfFirst { it >= M })
    }

    // 메모리를 target으로 한 0-1냅색 풀이
    private fun knapsack() {
        val (n, M) = readln().split(" ").map { it.toInt() }
        // 사용 중인 메모리의 바이트 수인 m1, ..., mN
        val memories = listOf(0) + readln().split(" ").map { it.toInt() }
        // 각 앱을 비활성화 했을 경우의 비용 c1, ..., cN
        val costs = listOf(0) + readln().split(" ").map { it.toInt() }

        // d : x만큼 memory 를 확보하기 위한 최소 cost 비용
        val d =
            IntArray(memories.sum() + 1) {
                costs.sum()
            }.also { it[0] = 0 }

        for (i in 1..n) {
            for (targetMem in memories.sum() downTo 1) {
                val mem = memories[i]
                if (targetMem >= mem) {
                    d[targetMem] = minOf(d[targetMem - mem] + costs[i], d[targetMem])
                }
            }
        }
//        println(d.toList())

        // M 이상 메모리를 확보하기 위한 최소 비용을 구하기 위해
        for (i in memories.sum() downTo 1) {
            if (d[i - 1] >= d[i]) d[i - 1] = d[i]
        }
        println(d[M])
    }

    private fun wrongAns() {
        // int 로도 쌉가능
        // n은 활성상태 앱 개수, M 사용 중인 메모리의 바이트 수의 최소합 limit
        val (n, M) = readln().split(" ").map { it.toInt() }
        // 사용 중인 메모리의 바이트 수인 m1, ..., mN
        val memories = readln().split(" ").map { it.toInt() }
        // 각 앱을 비활성화 했을 경우의 비용 c1, ..., cN
        val costs = readln().split(" ").map { it.toInt() }
        // 필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용
        var ans = memories.sum()

        // 1e9
        val d = IntArray(memories.sum() + 1) { memories.sum() }.apply { this[0] = 0 }
        val visited = BooleanArray(n)

        // O(1억 이상)
        fun minCost(cost: Int = 0) {
            for (i in 0 until n) { // 100
                if (visited[i].not()) {
                    if (d[cost] + costs[i] <= d[cost + memories[i]]) {
                        d[cost + memories[i]] = d[cost] + costs[i]
                        // ans 업데이트
                        if (cost + memories[i] >= M) ans = minOf(d[cost] + costs[i], ans)
                        visited[i] = true
                        minCost(cost + memories[i])
                        visited[i] = false
                    }
                }
            }
        }

        minCost()
        println(ans)
    }
}