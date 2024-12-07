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
                reduce(equation.second, listOf(plusOp,multiplyOp)).count { it == equation.first }
            )
        }.filter { it.second != 0 }.sumOf { it.first }
    }
    fun solve2(): Long {
        return this.equations.map { equation ->
            Pair(
                equation.first,
                reduce(equation.second, listOf(plusOp,multiplyOp,concatOp)).count { it == equation.first }
            )
        }.filter { it.second != 0 }.sumOf { it.first }
    }


    private fun reduce(equation : List<Long>, operations:List<(a:Long,b:Long) -> Long>) : List<Long>{
        if(equation.size == 1) return equation

        val resultList = mutableListOf<List<Long>>()
        for (op in operations) {
            val subList = equation.subList(1, equation.size).toMutableList()
            subList[0] = op(equation[0], equation[1])
            resultList.add(reduce(subList, operations))
        }
        return resultList.flatten()
    }

    private val plusOp     = { a:Long,b:Long -> a +b}
    private val multiplyOp = { a:Long, b:Long ->  a * b}
    private val concatOp   = { a:Long, b:Long -> "$a$b".toLong()}
}