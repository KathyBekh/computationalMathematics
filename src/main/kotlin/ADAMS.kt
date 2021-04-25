import org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator
import org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator

class Adams {

    /**
     * Данная функция использует библиотечное решение ДУ методом Адамса-Башфорта
     */
    fun adamsBashforth() {
        // Печать заголовка
        println("Адамса-Башфорта 2 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        // создание объекта класса AdamsBashforthIntegrator 2 порядка
        val ad = AdamsBashforthIntegrator(2, 0.0125, 0.05, 0.00001, 0.0001)
        val equation = RungeKuttaFehlburg.CoupledOdes()
        val stateVector = equation.initialConditions.clone()
        var t = 1.0
        while (t < 2.0) {
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
            ad.integrate(equation, 1.0, stateVector, 2.0, stateVector)
            t += 1.0
        }
        println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
    }

    /**
     * Данная функция использует библиотечное решение ДУ методом Адамса-Моултона
     */

    fun adamasMoulton(){
        println("Адамса-Моултона 2 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        val ad = AdamsMoultonIntegrator(2, 0.0125, 0.05, 0.00001, 0.0001)
        val equation = RungeKuttaFehlburg.CoupledOdes()
        val stateVector = equation.initialConditions.clone()
        var t = 1.0
        while (t < 2.0) {
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
            ad.integrate(equation, 1.0, stateVector, 2.0, stateVector)
            t += 1.0
        }
        println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
    }
}