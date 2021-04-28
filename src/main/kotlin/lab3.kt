
/**
         * Привести дифференциальное уравнение: 𝑡𝑦′′−(𝑡+1)𝑦′−2(𝑡−1)𝑦 = 0 к системе двух дифференциальных уравнений первого порядка.
         * Начальные условия: 𝑦(𝑡=1) = e2; 𝑦′(𝑡=1) = 2e2
         * Точное решение: 𝑦(𝑡) = 𝑒2t
         * Решить на интервале 1<= t <= 2
         * 1) используя программу RKF45 с шагом печати ℎ𝑝𝑟𝑖𝑛𝑡 = 0.1 и выбранной  погрешностью EPS в диапазоне 0.001 – 0.00001;
         * 2) Используя метод Адамса 2-й степени точности;
         * 3) используя метод Рунге-Кутты 3-й степени точности;
         * 4) используя метод Рунге-Кутты 4-й степени точности.
         * Исследовать влияние величины шага интегрирования ℎ𝑖𝑛𝑡 на величины локальной и глобальной погрешностей решения
         * заданного уравнения, для этого взять шаг вычисления ℎ𝑖𝑛𝑡 = (0.05, 0.025, 0.0125) .
     */

fun main() {

    val ode45 = RungeKuttaFehlburg(0.1, 1.0, 2.1)
    val ode34 = RungeKutta()
    val odeAdams = Adams()

    // функция для вывода результата в консоль
    fun printRes(name: String, solve: Map<String, Pair<Double, Double>>) {
        println(name)
        println("\tX\t\t\t\tY\t\t\t\tY'")
        for (key in solve.keys) {
            println("$key \t\t\t ${solve[key]}")
        }
    }

    printRes("Точное решение", solve())
    printRes("Адамса-Башфорта 2 порядка", odeAdams.adamsBashforth())
    println("Глобальная погрешность: ${globalError(localError(solve(), odeAdams.adamsBashforth()))}")
    printRes("Локальная погрешность", localError(solve(), odeAdams.adamsBashforth()))
    printRes("Адамса-Моултона 2 порядка", odeAdams.adamasMoulton())
    println("Глобальная погрешность: ${globalError(localError(solve(), odeAdams.adamasMoulton()))}")
    printRes("Локальная погрешность", localError(solve(), odeAdams.adamasMoulton()))
    printRes("Рунге-Кутты 3 порядка", ode34.rk3())
    println("Глобальная погрешность: ${globalError(localError(solve(), ode34.rk3()))}")
    printRes("Локальная погрешность", localError(solve(), ode34.rk3()))
    printRes("Рунге-Кутты 4 порядка", ode34.rk4())
    println("Глобальная погрешность: ${globalError(localError(solve(), ode34.rk4()))}")
    printRes("Локальная погрешность", localError(solve(), ode34.rk4()))
    printRes("Рунге-Кутты-Фельберга 5 порядка", ode45.rkf45())
    println("Глобальная погрешность: ${globalError(localError(solve(), ode45.rkf45()))}")
    printRes("Локальная погрешность", localError(solve(), ode45.rkf45()))
}