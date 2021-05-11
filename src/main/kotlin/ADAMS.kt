import org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator
import org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator

class Adams {

    /**
     * Данная функция использует библиотечное решение ДУ методом Адамса-Башфорта
     */
    fun adamsBashforth(): Map<String, Pair<Double, Double>>  {
        // создание объекта класса AdamsBashforthIntegrator 2 порядка
        val ad = AdamsBashforthIntegrator(2, 0.00625, 0.1, 0.00001, 0.0001)
        val equation = RungeKuttaFehlburg.CoupledOdes()
        val stateVector = equation.initialConditions.clone()
        val sol = mutableMapOf<String, Pair<Double, Double>>()
        sol["1.0"] = Pair(stateVector[0], stateVector[1])
        ad.integrate(equation, 1.0, stateVector, 2.0, stateVector)
        sol["2.0"] = Pair(stateVector[0], stateVector[1])
        return sol
    }

    /**
     * Данная функция использует библиотечное решение ДУ методом Адамса-Моултона
     */

    fun adamasMoulton(): Map<String, Pair<Double, Double>>{
        val ad = AdamsMoultonIntegrator(2, 0.00625, 0.1, 0.00001, 0.0001)
        val equation = RungeKuttaFehlburg.CoupledOdes()
        val stateVector = equation.initialConditions.clone()
        val sol = mutableMapOf<String, Pair<Double, Double>>()
        sol["1.0"] = Pair(stateVector[0], stateVector[1])
        ad.integrate(equation, 1.0, stateVector, 2.0, stateVector)
        sol["2.0"] = Pair(stateVector[0], stateVector[1])
        return sol
    }
}