package util

object TimeMeasurementUtil {
    inline fun measure(action: () -> Unit) {
        val start = System.currentTimeMillis()
        action()
        val end = System.currentTimeMillis()
        println("수행 시간: ${(end - start) / 1000.0}초")
    }
}