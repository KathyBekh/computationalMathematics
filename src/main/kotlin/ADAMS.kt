import org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator
import org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator

class Adams {
    fun adamsBashforth() {
        println("Адамса-Башфорта 2 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        val ad = AdamsBashforthIntegrator(2, 0.0125, 0.05, 0.001, 0.001)
        val eq = RungeKuttaFehlburg.CoupledOdes()
        val stateVector = eq.initialConditions.clone()
        var t = 1.0
        while (t < 2.0) {
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
            ad.integrate(eq, 1.0, stateVector, 2.0, stateVector)
            t += 1.0
        }
        println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
    }

    fun adamasMoulton(){
        println("Адамса-Моултона 2 порядка")
        println("\t\tX\t\t\tY\t\t\t\tY'")
        val ad = AdamsMoultonIntegrator(2, 0.0125, 0.05, 0.001, 0.001)
        val eq = RungeKuttaFehlburg.CoupledOdes()
        val stateVector = eq.initialConditions.clone()
        var t = 1.0
        while (t < 2.0) {
            println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
            ad.integrate(eq, 1.0, stateVector, 2.0, stateVector)
            t += 1.0
        }
        println(String.format("%10.1f \t\t%10.9f\t\t%10.9f", t, stateVector[0], stateVector[1]))
    }
}