import kotlin.math.pow
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
        .sumOf { it.second }
    }

    fun solve2() : Long {

        val targetExtensionVectorLength = sqrt(2*(10000000000000.0.pow(2)))
        return this.calculate(this.machines)
            .map { (it.first + (targetExtensionVectorLength / it.first)) * it.second}
            .sum().toLong()
    }

    private fun calculate(machines: List<Machine>) : List<Pair<Double, Long>> = machines.map { machine ->
        val findsX = equation(machine.a.first, machine.b.first, machine.target.first)
        val findsY = equation(machine.a.second, machine.b.second, machine.target.second)
        val calcVectorLength = vectorLength(machine.a, machine.b)
        val solutions = mutableSetOf<Pair<Double, Long>>()

        for (a in 1..100L) {
            for (b in 1..100L) {
                if (findsX.invoke(a, b) && findsY(a, b)) {
                    val vectorLength = calcVectorLength.invoke(a, b)
                    solutions.add(vectorLength to a * 3L + b)
                }
            }
        }

        solutions.minByOrNull { it.second }
    }
    .filterNotNull()
    .filter { it.second != 0L }


    private fun equation(aC:Long, bC: Long, tC : Long) : (Long, Long) -> Boolean =
        { a, b -> aC*a + bC*b == tC }

    private fun vectorLength(aV : LongVector, bV: LongVector) : (Long, Long) -> Double =
        { a, b -> sqrt((a*aV.first + b*bV.first).toDouble().pow(2) + (a*aV.second + b*bV.second).toDouble().pow(2)) }
}