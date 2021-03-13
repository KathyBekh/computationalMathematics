import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.LUDecomposition
import org.apache.commons.math3.linear.RealMatrix

/**
 * Написать процедуру формирования матрицы  A  по заданному вектору  B
 *     / 1  a_1  a_1 ... a_1
 *     { 1   1   a_2 ... a_2
 * A = {... ... ... ... ...      B = ( a_1 , a_2 , …, a_(n-1))^T
 *     { 1   1   1  ... a_(n-1)
 *     \ 1   1   1  ... 1
 * Задавая  n=5 , a_1 = 4 , a_2 = 3 , a_3 = 2 , a_4 = var = 1.5 ; 1.01 ; 1.001 ; 1.0001  и вычисляя  A^{-1}  с помощью
 * DECOMP  и  SOLVE  , найти нормы матриц R = A*A^{-1} - E  для всех вариантов  a_4 .
 **/

// Данная функция формирует новую матрицу А по заданному вектору В.
fun createMatrix(vectorB: Array<Double>): RealMatrix {
    val n = vectorB.size + 1
    val array = Array(n) { DoubleArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            array[i][j] = if (j > i) {
                vectorB[i]
            } else {
                1.0
            }
        }
    }

    return Array2DRowRealMatrix(array, false)
}

// Выводим результаты работы программы.
fun main() {
    for (i in arrayOf(1.5, 1.01, 1.001, 1.0001)) {
        // Вектор В из задания.
        val b = arrayOf(4.0, 3.0, 2.0, i)
        //вызов функции фоздания матрицы по вектору В.
        val a = createMatrix(b)
        // LU-разложение созданной матрицы.
        val lu = LUDecomposition(a)
        // Нахождение матрицы R = A*A^{-1}-E.
        val r = a.multiply(lu.solver.inverse).subtract(lu.p)

        // вывод на консоль матрицы A.
        println("матрица А: ")
        for (row in a.data) {
            println(row.toList())
        }
        // вывод на консоль матрицы A^{-1}.
        println("матрица А^{-1}:")
        for (row in lu.solver.inverse.data) {
            println(row.toList())
        }
        // вывод на консоль матрицы R.
        println("матрица R: ")
        for (row in r.data){
            println(row.toList())
        }
        // вывод в консоль нормы матрицы R.
        println("a_4 = ${i}, норма матрицы R = ${r?.norm}")
    }
}
