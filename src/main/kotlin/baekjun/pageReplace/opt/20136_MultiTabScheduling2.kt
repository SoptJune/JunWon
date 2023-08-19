//package baekjun.pageReplace.opt
//
//// https://www.acmicpc.net/problem/20136
//
//fun main() {
//    val (n, k) = readln().split(" ").map { it.toInt() }
//    val cmds = readln().split(" ").map { it.toInt() }.toIntArray()
//    val multiTabs = mutableMapOf<Int, Boolean>()
//    hashMapOf<Int, Int>().putIfAbsent(1, 2 + 1)
//    var ans = 0
//    cmds.forEachIndexed { i, c -> // O(N)
//        // 1) 일치하면 무시
//        if (multiTabs.getOrPut(c) { false }) return@forEachIndexed
//        // 2) multiTabs 중 empty가 있으면 채워 넣기
//        if (multiTabs.getOrDefault(0, false)) { // O(100)
//            multiTabs[multiTabs.indexOf(0)] = c // O(100)
//            return@forEachIndexed
//        }
//        // 가장 늦게 쓰는 element 교체 : OPT 페이지 교체 알고리즘
//        val rest = cmds.slice(i + 1 until cmds.size) // O(100)
//        var replacementCost = 0 // 교체 비용
//        var tabIdx = 0 // multiTabs에서의 위치
//        run {
//            multiTabs.forEachIndexed { j, tab -> // O(100)
//                if ((tab in rest).not()) { // 만약 tab이 rest의 element가 아닐 경우 교체(가장 늦게 쓰는 녀석이 확실!)
//                    tabIdx = j
//                    return@run
//                }
//                val tabCost = rest.indexOf(tab) // O(100)
//                if (replacementCost <= tabCost) { // 가장 늦게 쓰는 녀석인지 비교
//                    replacementCost = tabCost
//                    tabIdx = j
//                }
//            }
//        }
//        // 교체
//        multiTabs[tabIdx] = c
//        ans++
//    }
//    println(ans)
//}
//
