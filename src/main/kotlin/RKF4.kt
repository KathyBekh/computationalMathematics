import kotlin.math.exp
import kotlin.math.roundToInt

class RungeKutta {

    /**
     * функции, которые получаются из системы
     */
    private fun f(x: Double, y: Double, z: Double): Double {
        return ((x + 1) * z + 2 * (x - 1) * y) / x
    }

    private fun g(x: Double, y: Double, z: Double): Double {
        return z
    }

    /**
     * Начальные условия
     */
    private val stateVector = doubleArrayOf(exp(2.0), 2 * exp(2.0))

    /**
     * Конец промкжутка интегрирования и шаг интегрирования
     */
    private val start = 1.0
    private val end = 2.1
    private val h: Double = 0.1

    /**
     * Реализация метода Рунге-Кутты 4 порядка
     */
    fun rk4(): Map<String, Pair<Double, Double>> {
        // инициализация начальных условий
        var y0 = stateVector[0]
        var z0 = stateVector[1]
        var x0: Double = start
        var y1: Double
        var z1: Double
        // инициализация коэффициентов
        val coefficients = mutableMapOf("k1" to 0.0, "k2" to 0.0, "k3" to 0.0, "k4" to 0.0,
            "q1" to 0.0, "q2" to 0.0, "q3" to 0.0, "q4" to 0.0,)
        val sol = mutableMapOf<String, Pair<Double, Double>>()
        // интегрирование заданной функции и вывод результата по зашагам
        do {
            coefficients["k1"] = h * f(x0, y0, z0)
            coefficients["q1"] = h * g(x0, y0, z0)
            coefficients["k2"] = h * f(x0 + h / 2.0, y0 + coefficients["q1"]!! / 2.0, z0 + coefficients["k1"]!! / 2.0)
            coefficients["q2"] = h * g(x0 + h / 2.0, y0 + coefficients["q1"]!! / 2.0, z0 + coefficients["k1"]!! / 2.0)
            coefficients["k3"] = h * f(x0 + h / 2.0, y0 + coefficients["q2"]!! / 2.0, z0 + coefficients["k2"]!! / 2.0)
            coefficients["q3"] = h * g(x0 + h / 2.0, y0 + coefficients["q2"]!! / 2.0, z0 + coefficients["k2"]!! / 2.0)
            coefficients["k4"] = h * f(x0 + h, y0 + coefficients["q3"]!!, z0 + coefficients["k3"]!!)
            coefficients["q4"] = h * g(x0 + h, y0 + coefficients["q3"]!!, z0 + coefficients["k3"]!!)
            z1 = z0 + (coefficients["k1"]!! + 2.0 * coefficients["k2"]!! + 2.0 * coefficients["k3"]!! + coefficients["k4"]!!) / 6.0
            y1 = y0 + (coefficients["q1"]!! + 2.0 * coefficients["q2"]!! + 2.0 * coefficients["q3"]!! + coefficients["q4"]!!) / 6.0
            val round = ((x0 * 10.0).roundToInt() / 10.0).toString()
            sol[round] = Pair(y0, z0)
            y0 = y1
            z0 = z1
            x0 += h
        } while (x0 < end)
        return sol
    }

    /**
     * Реализация метода Рунге-Кутты 3 порядка
     */
    fun rk3(): Map<String, Pair<Double, Double>> {
        // инициализация начальных условий
        var x0: Double = start
        var y0 = stateVector[0]
        var z0 = stateVector[1]
        var y1: Double
        var z1: Double
        // инициализация коэффициентов
        val coefficients = mutableMapOf("k1" to 0.0, "k2" to 0.0, "k3" to 0.0,
            "q1" to 0.0, "q2" to 0.0, "q3" to 0.0)
        val sol = mutableMapOf<String, Pair<Double, Double>>()
        // интегрирование заданной функции и вывод результата по зашагам
        do {
            coefficients["k1"] = h * f(x0, y0, z0)
            coefficients["q1"] = h * g(x0, y0, z0)
            coefficients["k2"] = h * f(x0 + h / 2.0, y0 + coefficients["q1"]!! / 2.0,
                z0 + coefficients["k1"]!! / 2.0)
            coefficients["q2"] = h * g(x0 + h / 2.0, y0 + coefficients["q1"]!! / 2.0,
                z0 + coefficients["k1"]!! / 2.0)
            coefficients["k3"] = h * f(x0 + h, y0 + coefficients["q2"]!! * 2.0 - coefficients["k1"]!!,
                z0 + coefficients["k2"]!! * 2.0 - coefficients["k1"]!!)
            coefficients["q3"] = h * g(x0 + h, y0 + coefficients["q2"]!! * 2.0 - coefficients["k1"]!!,
                z0 + coefficients["k2"]!! * 2.0 - coefficients["k1"]!!)

            z1 = z0 + (coefficients["k1"]!! + 4.0 * coefficients["k2"]!! + coefficients["k3"]!!) / 6.0
            y1 = y0 + (coefficients["q1"]!! + 4.0 * coefficients["q2"]!! + coefficients["q3"]!!) / 6.0
            val round = ((x0 * 10.0).roundToInt() / 10.0).toString()
            sol[round] = Pair(y0, z0)
            y0 = y1
            z0 = z1
            x0 += h
        } while (x0 < end)
        return sol
    }
}
