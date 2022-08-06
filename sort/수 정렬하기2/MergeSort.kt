import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val n = br.readLine().toInt()
    val arr = IntArray(n) // 영진이 피드백 반영 ㅋㅋㅋㅋ
    for (i in 0 until n) {
        arr[i] = br.readLine().toInt()
    }
    val ansArr = mergeSort(arr)

    ansArr.forEach { bw.write("$it\n") }
    bw.flush()
    bw.close()
    br.close()

}

private fun mergeSort(arr: IntArray): IntArray {

    if (arr.size <= 1) return arr

    val mid = arr.size / 2
    val left = mergeSort(arr.copyOfRange(0, mid)) // 이 부분에서 array 슬라이싱하는 더 좋은 방법이 있으면 추천해주세요!!
    val right = mergeSort(arr.copyOfRange(mid, arr.size))

    var i = 0;
    var j = 0;
    var k = 0;

    while (i < left.size && j < right.size) {
        if (left[i] < right[j]) {
            arr[k] = left[i]
            i++
            k++
        } else {
            arr[k] = right[j]
            j++
            k++
        }
        if (i == left.size) {
            while (j < right.size) {
                arr[k] = right[j]
                j++
                k++
            }
        } else if (j == right.size) {
            while (i < left.size) {
                arr[k] = left[i]
                i++
                k++
            }
        }
    }
    return arr
}

