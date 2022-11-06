
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
            val stepTwoMusicList = stepOneMusicList.sortedByDescending { musicPair ->
                musicPair.second
            }.take(2)
            // 3. 재생수 같으면 고유 번호 작은 수 (고유 번호) - 오름차순
            if (stepTwoMusicList.size == 1) return@map stepTwoMusicList.map { pair -> pair.first }
            if (stepTwoMusicList[0].second != stepTwoMusicList[1].second) return@map stepTwoMusicList.map { pair -> pair.first }
            if (stepTwoMusicList[0].first <= stepTwoMusicList[1].first) return@map stepTwoMusicList.map { pair -> pair.first }

            Collections.swap(stepTwoMusicList, 0, 1)
            stepTwoMusicList.map { pair -> pair.first }
        }
        .flatten().toIntArray()
}}