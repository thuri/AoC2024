import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

typealias LongVector = Pair<Long, Long>

class Day13(example: String) {

    data class Machine (val a: LongVector, val b: LongVector, val target: LongVector)

    private val coordinateRegex = Regex("""X.(\d+), Y.(\d+)""")

    private val machines = example.lines()
        .filter { line -> line.isNotBlank() }
        .fold(mutableListOf<MutableList<LongVector>>()) { vectors, line ->
        val currentMachineVectors : MutableList<LongVector> =
            if(line.startsWith("Button A:")) {
                mutableListOf()
            } else {
                vectors.removeLast()
            }
        val groups = coordinateRegex.find(line)?.groupValues!!
        currentMachineVectors.add(LongVector(groups[1].toLong(), groups[2].toLong()))

        vectors.addLast(currentMachineVectors)
        vectors
    }.map { vectors ->
        Machine(vectors[0], vectors[1], vectors[2])
    }

    fun solve(): Long {
        return calculate(this.machines)
        .sumOf { it }
    }

    fun solve2() : Long {
        val extension = 10000000000000
        return calculate(this.machines.map {
            Machine(it.a, it.b, it.target + extension)
        }).sumOf { it }
    }

    private fun calculate(machines: List<Machine>) : List<Long> = machines.map { machine ->
        val xA = machine.a.first.toDouble()
        val yA = machine.a.second.toDouble()
        val xB = machine.b.first.toDouble()
        val yB = machine.b.second.toDouble()
        val xT = machine.target.first.toDouble()
        val yT = machine.target.second.toDouble()

        /*
         * using gaussian elimination we get the following equation
         *
         * xA xB             | xT   / * -yA/xA -
         * yA yB             | yT        <--+--|
         * --------------------------
         * xA xB             | xT
         * 0  yB-(xB*yA)/xA  | yT-(xT*yA)/xA
         * --------------------------
         * => a*0 + b*(yB-(xB*yA)/xA) = yT-(xT*yA)/xA
         *                          b = (yT-(xT*yA)/xA)/(yB-(xB*yA)/xA)
         *
         * the calculation must be done with double's and the result rounded to Long
         */
        val b = round((yT - (xT * yA / xA)) / (yB - (xB * yA / xA))).toLong()
        val a = round((xT - b*xB) / xA).toLong()

        if( a*xA + b*xB == xT && a*yA + b*yB == yT)
            return@map a * 3L + b
        else
            return@map 0
    }
    .filter { it != 0L }

    operator fun LongVector.plus(scalar : Long) : LongVector {
        return LongVector(this.first + scalar, this.second + scalar)
    }
}