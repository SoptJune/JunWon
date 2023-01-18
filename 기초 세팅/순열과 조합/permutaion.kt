fun main() {
    println(permutation(listOf(1,2,3,4,5))) 
    // 출력값 [[1, 2, 3, 4, 5], [1, 2, 3, 5, 4], [1, 2, 4, 3, 5] ... ]
    println(permutation(listOf(1,2,3,4,5), target = 5))
    // 출력값 : 위와 같음
    println(permutation(listOf(1,2,3,4,5), target = 4))
    // 출력값 : [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 3], ...
    println(permutation(listOf(1,2,3,4,5), target = 6))
    // 출력값 : []
}

fun <T> permutation(
    el: List<T>,
    fin: List<T> = listOf(),
    sub: List<T> = el,
    target: Int = el.size
): List<List<T>> {
    return if (target == 0) listOf(fin)
    else sub.flatMap { permutation(el, fin + it, sub - it, target - 1) }
}