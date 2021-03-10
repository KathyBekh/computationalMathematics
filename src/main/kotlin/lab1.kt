import org.apache.commons.math3.analysis.UnivariateFunction
import org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm
import org.apache.commons.math3.analysis.solvers.BisectionSolver

/**
Для таблично заданной функции  f(x)
x     0.0  0.2  0.5  0.7  1.0  1.3  1.7  2.0
f(x)  1.0  1.1487  1.4142  1.6245  2,0000  2.4623  3.2490  4.0000
построить а) сплайн-функцию; б) полином Лагранжа 7-й степени и использовать обе аппроксимирующие функции для нахождения
корня уравнения f(x) + 5x - 3 = 0     на промежутке  [0 , 2]  методом бисекции. Результаты двух вариантов нахождения
корня сравнить для одинаковых задаваемых вами значений погрешности метода бисекции.
 **/
fun main() {
    val xDouble = doubleArrayOf(0.0, 0.2, 0.5, 0.7, 1.0, 1.3, 1.7, 2.0)
    val yDouble = doubleArrayOf(1.0, 1.1487, 1.4142, 1.6245, 2.0, 2.4623, 3.249, 4.0)

    // Сплайн интерполяция функции таблично заданной функции.
    val spline = AkimaSplineInterpolator().interpolate(xDouble, yDouble)
    // Интерполяция таблично заданной функции полиномом Лагранжа 7й степени.
    val lagrange = PolynomialFunctionLagrangeForm(xDouble, yDouble)

    // Метод бисекции для нахождения корней функции f(x) + 5x - 3 = 0
    val solver = BisectionSolver(1e-10)
    val splineResult = solver.solve(10000, equation(spline), 0.0, 2.0)
    val lagrangeResult = solver.solve(10000, equation(lagrange), 0.0, 2.0)

    println("Коэффициенты функции интерполирования по Лагранжу 7й степени:")
    var count = 0
    for (i in lagrange.coefficients){
        println("a$count = $i")
        count += 1
    }
    println("Бисекция сплан-функции: х = $splineResult")
    println("Бисекция лагранж-функции: х = $lagrangeResult")
}

//Замена f(x) в уравнение f(x) + 5x - 3 = 0 на интерполяционную функцию.
fun equation(f: UnivariateFunction): UnivariateFunction {
    return UnivariateFunction { x -> f.value(x) + 5 * x - 3 }
}
