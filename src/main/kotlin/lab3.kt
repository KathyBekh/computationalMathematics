fun main() {

    val ode45 = RungeKuttaFehlburg(0.1, 1.0, 2.1)
    ode45.rkf45()

    val ode34 = RungeKutta()
    ode34.rk4()
    ode34.rk3()

    val odeAdams = Adams()
    odeAdams.adamsBashforth()
    odeAdams.adamasMoulton()
}