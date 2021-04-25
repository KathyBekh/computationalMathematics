import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.roundToInt

class RungeKutta {
    private val k = 2
    private var Yo: Double = 0.0
    private var Y1: Double = 0.0
    private var Zo: Double = 0.0
    private var Z1: Double = 0.0
    private var k1: Double = 0.0
    private var k2: Double = 0.0
    private var k4: Double = 0.0
    private var k3: Double = 0.0
    private var q1: Double = 0.0
    private var q2: Double = 0.0
    private var q4: Double = 0.0
    private var q3: Double = 0.0

    fun rk4() {
        Yo = exp(2.0)
        Zo = 2 * exp(2.0)
        var Xo: Double = 1.0
        val h: Double = 0.1
//        val coefficients = mapOf<String, Double>()
        println("Рунге-Кутты 4 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", r(Xo, k), Yo, Zo))
        while (r(Xo, 2) < 2.0) {
            k1 = h * f(Xo, Yo, Zo)
            q1 = h * g(Xo, Yo, Zo)
            k2 = h * f(Xo + h / 2.0, Yo + q1 / 2.0, Zo + k1 / 2.0)
            q2 = h * g(Xo + h / 2.0, Yo + q1 / 2.0, Zo + k1 / 2.0)
            k3 = h * f(Xo + h / 2.0, Yo + q2 / 2.0, Zo + k2 / 2.0)
            q3 = h * g(Xo + h / 2.0, Yo + q2 / 2.0, Zo + k2 / 2.0)
            k4 = h * f(Xo + h, Yo + q3, Zo + k3)
            q4 = h * g(Xo + h, Yo + q3, Zo + k3)
            Z1 = Zo + (k1 + 2.0 * k2 + 2.0 * k3 + k4) / 6.0
            Y1 = Yo + (q1 + 2.0 * q2 + 2.0 * q3 + q4) / 6.0
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", r(Xo + h, k), Y1, Z1))
            Yo = Y1
            Zo = Z1
            Xo += h
        }
    }

    fun rk3() {
        var Xo: Double = 1.0
        Yo = exp(2.0)
        Zo = 2 * exp(2.0)
        val h: Double = 0.1

        println("Рунге-Кутты 3 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", r(Xo, k), Yo, Zo))
        while (r(Xo, 2) < 2.0) {
            k1 = h * f(Xo, Yo, Zo)
            q1 = h * g(Xo, Yo, Zo)
            k2 = h * f(Xo + h / 2.0, Yo + q1 / 2.0, Zo + k1 / 2.0)
            q2 = h * g(Xo + h / 2.0, Yo + q1 / 2.0, Zo + k1 / 2.0)
            k3 = h * f(Xo + h, Yo + q2 * 2.0 - k1, Zo + k2 * 2.0 - k1)
            q3 = h * g(Xo + h, Yo + q2 * 2.0 - k1, Zo + k2 * 2.0 - k1)

            Z1 = Zo + (k1 + 4.0 * k2 + k3) / 6.0
            Y1 = Yo + (q1 + 4.0 * q2 + q3) / 6.0
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", r(Xo + h, k), Y1, Z1))
            Yo = Y1
            Zo = Z1
            Xo += h
        }
    }

    /**
     * функция для округления и отбрасывания "хвоста"
     */
    fun r(value: Double, k: Int): Double {
        return (10.0.pow(k.toDouble()) * value).roundToInt().toDouble() / 10.0.pow(k.toDouble())
    }

    /**
     * функции, которые получаются из системы
     */
    fun f(x: Double, y: Double, z: Double): Double {
        return ((x + 1) * z + 2 * (x - 1) * y) / x
    }

    fun g(x: Double, y: Double, z: Double): Double {
        return z
    }
}
