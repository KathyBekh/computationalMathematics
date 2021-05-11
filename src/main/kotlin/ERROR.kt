import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.roundToInt

/**
 * Точное решение
 */
fun solve(): Map<String, Pair<Double, Double>> {
    var t = 1.0
    val h = 0.1
    val sol = mutableMapOf<String, Pair<Double, Double>>()
    while (t < 2.1) {
        val round = ((t * 100.0).roundToInt() / 100.0).toString()
        sol[round] = Pair(exp(2 * t), 2 * exp(2 * t))
        t += h
    }
    return sol
}

/**
 * Функция определения локальной погрешности
 */

fun localError(exactSol: Map<String, Pair<Double, Double>>, approximateSol: Map<String, Pair<Double, Double>>): Map<String, Pair<Double, Double>> {
    val result = mutableMapOf<String, Pair<Double, Double>>()
    for (key in approximateSol.keys) {
        val exact = exactSol[key]
        val approximate = approximateSol[key]
        result[key] = Pair(abs((exact?.first ?: 0.0) - (approximate?.first ?: 0.0)),
            abs((exact?.second ?: 0.0) - (approximate?.second ?: 0.0)))
    }
    return result
}

/**
 * Функция определения глобальгой погрешности
 */

fun globalError(localErrors: Map<String, Pair<Double, Double>>): Pair<Double, Double> {
    var firstSum = 0.0
    var secondSum = 0.0
    for ((first, second) in localErrors.values) {
        firstSum += first
        secondSum += second
    }
    return Pair(firstSum, secondSum)
}