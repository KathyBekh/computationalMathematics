import org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

internal class DrawingComponent : JPanel() {
    var xg = DrawFunction.x
    var yg = DrawFunction.y
    var ng = DrawFunction.n
    override fun paintComponent(gh: Graphics) {
        // Координатные оси.
        val drp = gh as Graphics2D
        drp.drawLine(20, 460, 20, 20)
        drp.drawLine(20, 460, 460, 460)
        // кривая графика.
        drp.drawPolyline(xg, yg, ng)
    }
}
val xDouble = doubleArrayOf(0.0, 0.2, 0.5, 0.7, 1.0, 1.3, 1.7, 2.0)
val yDouble = doubleArrayOf(1.0, 1.1487, 1.4142, 1.6245, 2.0, 2.4623, 3.249, 4.0)

// Сплайн интерполяция функции таблично заданной функции.
val spline: PolynomialSplineFunction = AkimaSplineInterpolator().interpolate(xDouble, yDouble)

fun yInt(): IntArray {
    val y = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    var count = 0
    for(knot in spline.knots) {
        y[count] = (knot * 100 + 20).toInt()
        count += 1
    }
    return y
}
fun xInt(): IntArray {
    val y = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    var count = 0
    for (yi in yDouble) {
        y[count] = (460 - yi * 100).toInt()
        count += 1
    }
    return y
}

class DrawFunction : JFrame("График по точкам") {
    companion object {
        var x = yInt()
        var y = xInt()
        var n = 8
    }

    init {
        val jcp = JPanel(BorderLayout())
        contentPane = jcp
        jcp.add(DrawingComponent(), BorderLayout.CENTER)
        jcp.background = Color.gray
        setSize(50, 40)
        setLocationRelativeTo(null)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }
}