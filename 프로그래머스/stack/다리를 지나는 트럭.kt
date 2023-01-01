import java.util.*

fun main() {
    println(
        solution(2, 10, intArrayOf(7, 4, 5, 6))
    )
    println(solution(100, 100, intArrayOf(10)))
    println(solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)))
}

// bridge_length는 1 이상 10,000 이하입니다.
// weight는 1 이상 10,000 이하입니다.
// truck_weights의 길이는 1 이상 10,000 이하입니다.
// 모든 트럭의 무게는 1 이상 weight 이하입니다.
// 트럭의 순서를 바꿀 수는 없음
// 트럭들은 FIFO이므로 Queue를 사용해야할 듯
// 한 번에 여러 대의 트럭이 올라갈 수 있음 (시... 아니네)

// 대기하고 있는 트럭이 있는가?
// 다리 위 트럭이 다리를 다 건넜는지 체크
// 트럭이 다리에 올라갈 수 있는 지 체크 (이 때, 트럭이 여러 대 올라갈 수 있음)
private var weightLimit = 0
private var bridgeLength = 0
var curTime = 1
var totalWeight = 0
private lateinit var crossingTrucks: LinkedList<Truck>
private lateinit var remainTrucks: LinkedList<Int>
fun solution(bridgeLen: Int, wLimit: Int, trucks: IntArray): Int {
    curTime = 1
    totalWeight = 0
    weightLimit = wLimit
    bridgeLength = bridgeLen
    crossingTrucks = LinkedList<Truck>()
    remainTrucks = LinkedList<Int>(trucks.toList())
    while (remainTrucks.isNotEmpty() || crossingTrucks.isNotEmpty()) {
        crossBridge() // 1. 다리 건너기
        if (remainTrucks.isEmpty()) { // 2. 대기 중인 트럭이 없다면?
            curTime = bridgeLength + crossingTrucks.last.time
            break
        }
        loadTruck() // 3. 대기중인 트럭 다리에 올리기
    }
    return curTime
}

fun crossBridge() {
    if (crossingTrucks.isNotEmpty()) {
        val curCrossingTruck = crossingTrucks.peek()!!
        if (curTime - curCrossingTruck.time == bridgeLength) { // 다리를 건널 수 있는지?
            totalWeight -= curCrossingTruck.weight // 다리 위 트럭 무게 빼기
            crossingTrucks.poll() // 다리 위 트럭 빼기
        }
    }
}

fun loadTruck() {
    if (remainTrucks.isNotEmpty()) {
        val loadedTruckWeight = remainTrucks.peek()!! // 대기 중인 트럭 무게
        if (weightLimit >= loadedTruckWeight + totalWeight) { // 건넌 트럭 무게 + 현재 트럭 무게가 무게 제한을 넘지 않는지 확인
            crossingTrucks.add(Truck(curTime, loadedTruckWeight)) // 트럭 다리 위에 올리기
            totalWeight += loadedTruckWeight // 다리 위 트럭 무게 더하기
            remainTrucks.poll() // 대기 중인 트럭 빼기
            curTime++ // 시간 증가
        } else {
            curTime = bridgeLength + crossingTrucks.peek()!!.time // 다리 위 트럭이 다리를 다 건널 때까지 시간 증가
        }
    }
}

data class Truck(val time: Int, val weight: Int)