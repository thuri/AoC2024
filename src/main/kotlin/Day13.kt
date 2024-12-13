class Day13(example: String) {

    data class Machine (val a: Vector, val b: Vector, val target: Vector)

    private val coordinateRegex = Regex("""X.(\d+), Y.(\d+)""")

    private val machines = example.lines()
        .filter { line -> line.isNotBlank() }
        .fold(mutableListOf<MutableList<Vector>>()) { vectors, line ->
        val currentMachineVectors : MutableList<Vector> =
            if(line.startsWith("Button A:")) {
                mutableListOf()
            } else {
                vectors.removeLast()
            }
        val groups = coordinateRegex.find(line)?.groupValues!!
        currentMachineVectors.add(Vector(groups[1].toInt(), groups[2].toInt()))

        vectors.addLast(currentMachineVectors)
        vectors
    }.map { vectors ->
        Machine(vectors[0], vectors[1], vectors[2])
    }

    fun solve(): Long {

        return this.machines.map { machine ->
            val findsX = equation(machine.a.first, machine.b.first, machine.target.first)
            val findsY = equation(machine.a.second, machine.b.second, machine.target.second)
            val solutions = mutableListOf<Long>()
            for (a in 1..100) {
                for (b in 1..100) {
                    if (findsX.invoke(a, b) && findsY(a, b))
                        solutions.add(a * 3L + b)
                }
            }
            solutions.minOfOrNull { it } ?: 0
        }
        .filter { it != 0L }
        .sumOf { it }
    }

    private fun equation(aC:Int, bC: Int, tC : Int) : (Int, Int) -> Boolean =
        { a, b -> aC*a + bC*b == tC }
}