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
 * DECOMP  и  SOLVE  , найти нормы матриц R = AA^{-1} - E  для всех вариантов  a_4 .
 **/

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

fun main() {
    for (i in arrayOf(1.5, 1.01, 1.001, 1.0001)) {
        val b = arrayOf(4.0, 3.0, 2.0, i)
        val a = createMatrix(b)
        val lu = LUDecomposition(a)
        val r = a.multiply(lu.solver.inverse).subtract(lu.p)
        println("a_4 = ${i}, norm = ${r?.norm}")
    }
}
