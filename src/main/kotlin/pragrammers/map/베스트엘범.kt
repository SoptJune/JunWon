  // 1) genre 내 노래 수
    // 2) genre 내 노래 수 play가 가장 큰 거 -> 내림차순
    // 3) index 값이 곧 번호, 번호가 낮은 거 부터 
    // 1 이상 10,000 이
    // 장르 100종류 미만
    // 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
    // 모든 장르는 재생된 횟수가 다릅니다.
    // 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        return genres
            .indices
            .map{it to plays[it]}
            .groupBy{ genres[it.first]}
            .values
            .sortedBy { p -> p.sumOf{-it.second} } // 1번 조건
            .flatMap{ it.sortedWith(compareBy({-it.second}, {it.first})).take(2)}
            .map{it.first}
            .toIntArray()
    }