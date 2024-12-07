class Day7(exampleInput: String) {

    private val equations : MutableList<Pair<Long, List<Long>>>

    init {
        val regex = Regex("""(\d+)""")
        equations = exampleInput.lines().map { line ->
            val values = regex.findAll(line).map { it.groupValues[1].toLong()}.toList()
            return@map values[0] to values.subList(1, values.size)
        }.toMutableList()

    }

    fun solve1(): Long {
        return this.equations.map { equation ->
            Pair(
                equation.first,
                reduce(equation.second).count { it == equation.first }
            )
        }.filter { it.second != 0 }.sumOf { it.first }
    }

    private fun reduce(equation : List<Long>) : List<Long>{
        if(equation.size == 1) return equation

        val resultList = mutableListOf<List<Long>>()
        for (op in listOf(plusOp,multiplyOp)) {
            val subList = equation.subList(1, equation.size).toMutableList()
            subList[0] = op(equation[0], equation[1])
            resultList.add(reduce(subList))
        }
        return resultList.flatten()
    }

    private val plusOp     = { a:Long,b:Long -> a +b}
    private val multiplyOp = { a:Long, b:Long ->  a * b}
}