import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main() {
    val day2 = Day2()
    day2.puzzle1()
    day2.example2()
    day2.puzzle2() // 258 too low
}

class Day2 {

    private val reports = mutableListOf<List<Long>>()

    private fun parseInput(input: String) {
        reports.clear()
        val lineRegex = """(\d+)""".toRegex()
        input.lines().map { line ->
            reports.add(lineRegex.findAll(line).map { it.groupValues[1].toLong() }.toList())
        }
    }

    fun puzzle1() {
        this.parseInput(Day2::class.java.getResource("/Day2.txt")?.readText()!!)
        reports.map { report ->  isSafe(report) }.count {it}.let { println(it) }
    }

    fun example2() {
        val text = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
        this.parseInput(text)
        println(countWithDampener())
    }
    fun puzzle2() {
        this.parseInput(Day2::class.java.getResource("/Day2.txt")?.readText()!!)
        println(countWithDampener())
    }


    private fun countWithDampener() = reports.map { report ->
        var isSafe = isSafe(report)
        if (!isSafe) {
            val subreports = mutableListOf<List<Long>>()
            for (r in 0..report.size) {
                subreports.add(report.filterIndexed { index, _ -> index != r })
            }
            isSafe = subreports.firstOrNull() { isSafe(it) } != null
        }
        return@map isSafe
    }.count { it }


    private fun isSafe(report: List<Long>) : Boolean {
        return abs(report
            .mapIndexed {index, value -> value - report[max(0, index - 1)] }
            .filterIndexed {index, _ -> index != 0}
            .map { it.sign * ( if( abs(it) in 1 .. 3) 1 else 0) }
            .sum()) == report.size - 1
    }

    /*private fun isSafeWithDamper(reportNo: Int, report: List<Long>) : Boolean {
        val temp = report
            .mapIndexed {index, value ->
                val next = report.getOrElse(index + 1) { value }
                val afterNext = report.getOrElse(index + 2) { 0 }
                Pair(next - value, afterNext - value)
            }.filterIndexed() {index, _ -> index != report.size - 1}
            .map {  }

        return abs(temp.sum()) == temp.size
    }*/
}