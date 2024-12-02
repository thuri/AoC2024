import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main() {
    val day2 = Day2()
    day2.puzzle1()
    day2.puzzle2()
}

class Day2 {

    private val reports = mutableListOf<List<Long>>()

    init {
        val lineRegex = """(\d+)""".toRegex()
        Day2::class.java.getResource("/Day2.txt")?.readText()?.let { file ->
            file.lines().map { line ->
                reports.add(lineRegex.findAll(line).map { it.groupValues[1].toLong() }.toList())
            }
        }
    }

    fun puzzle1() {
        reports.mapIndexed { index, report ->  isSafe(index,report) }.count { it }
    }

    private fun isSafe(reportNo: Int, report: List<Long>) : Boolean {
        val temp = report
            .mapIndexed {index, value -> value - report[max(0, index - 1)] }
            .filterIndexed {index, _ -> index != 0}
            .map { it.sign * ( if( abs(it) in 1 .. 3) 1 else 0) }

        return abs(temp.sum()) == temp.size
    }

    fun puzzle2() {

    }
}