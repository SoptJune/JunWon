
import java.util.*
class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
    // 0. [장르 이름 = [(id, playNums)]]
    return plays.mapIndexed { id, musicNums -> id to musicNums }
        .groupBy { musicPair ->
            genres[musicPair.first]
        } // 1. 노래 수 많은 수 (장르) - 내림차순
        .values.sortedByDescending { stepOneMusicList: List<Pair<Int, Int>> ->
            stepOneMusicList.map { pair -> pair.second }.reduce { total, playNum -> total + playNum }
        } // 2. 재생 수 많은 수 (노래) - 내림차순
        .map { stepOneMusicList: List<Pair<Int, Int>> ->
            stepOneMusicList.sortedWith(
                compareBy(
                    {
                        -it.second
                    }, { it.first }
                )).take(2).map { it.first }
        }.flatten().toIntArray()
    }
}