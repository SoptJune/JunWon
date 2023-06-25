package programmers.sort
internal class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map { command ->
            val (i, j, k) = command.map{it - 1}
            array.slice(i .. j).sorted()[k]
        }.toIntArray()
    }
}