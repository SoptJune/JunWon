package baekjun.dp
// 홍보를 할 수 있는 도시 : baekjun.getN
// c <= 1000 , baekjun.getN <= 20
//  도시별로 홍보하는데 드는 비용과, 그 때 몇 명의 호텔 고객이 늘어나는지
// 9 3 -> 9원으로 3명 고객 증가
// 각 도시에는 무한 명의 잠재적인 고객
// 호텔의 고객을 적어도 C명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램
// C는 1,000보다 작거나 같은 자연수이
// 투자해야 하는 돈의 최솟값
// 도시는 총 20개 이하.. 
// 중복 발생 -> dp..? 
// Dn = min(Ai + Dn-i) (i는 1~20), O = 1000 * 20, dn은 n명을 늘리기 위해 필요한 최소 비용
//  적어도 C명 이상~
private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()

fun main() {
    val (target, n) = br.readLine().split(" ").map { it.toInt() }
    val li = mutableListOf<Pair<Int, Int>>().apply {
        repeat(n) {
            val (c, p) = br.readLine().split(" ").map { it.toInt() }
            add(c to p)
        }
    }
    // 1100으로 범위를 잡은 이유는 d[1000]보다 d[1099]가 더 작을 수 있기 때문 ㅎ ㅎ
  val d = IntArray(1000 + 101) { 1000_001 }.apply { this[0] = 0 }
    for (i in 1..1000 + 100) {
        for ((c, n) in li) {
            if (i < n) continue
            d[i] = minOf(d[i], c + d[i - n])
        }
    }

    bw.write("${d.drop(target).min()}\n")
    bw.close()
    br.close()
}