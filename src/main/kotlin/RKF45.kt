import org.apache.commons.math3.exception.DimensionMismatchException
import org.apache.commons.math3.exception.MaxCountExceededException
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations
import org.apache.commons.math3.ode.nonstiff.LutherIntegrator
import kotlin.math.exp

class RungeKuttaFehlburg(
    private val step: Double,
    private val minStep: Double,
    private val maxStep: Double) {


    fun rkf45() {
        // Печать заголовка для ориентации в выводе
        println("Рунге-Кутты-Фельберга 5 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        val maxTime = maxStep
        // вызов библиотечной функции rkf45 с заданным шагом
        val firstOrderIntegrator = LutherIntegrator(step)
        val odes: FirstOrderDifferentialEquations = CoupledOdes()
        val y = (odes as CoupledOdes).initialConditions.clone()
        var t = minStep
        // интегрирование заданной функции и вывод результата по зашагам
        while (t < maxTime) {
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, y[0], y[1]))
            firstOrderIntegrator.integrate(odes, t, y, t + step, y)
            t += step
        }
    }
 // внутренний класс определяющий заданную функцию и начальные условия

    internal class CoupledOdes : FirstOrderDifferentialEquations {

        val initialConditions: DoubleArray
            get() = doubleArrayOf(exp(2.0), 2 * exp(2.0))

        override fun getDimension(): Int {
            return 2
        }

        @Throws(MaxCountExceededException::class, DimensionMismatchException::class)
        override fun computeDerivatives(t: Double, y: DoubleArray, yDot: DoubleArray) {
            yDot[0] = y[1]
            yDot[1] = ((t + 1) * y[1] + 2 * (t - 1) * y[0]) / t
        }
    }
}
