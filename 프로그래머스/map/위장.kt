// https://school.programmers.co.kr/learn/courses/30/lessons/42578?language=kotlin
fun solution(clothes: Array<Array<String>>): Int {
    return clothes
            .groupingBy { it[1] }
            .eachCount()
            .values
            .fold(1) { re, arr ->re * (arr + 1)} - 1
}