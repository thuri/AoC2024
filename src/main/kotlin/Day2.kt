import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main() {
    val day2 = Day2()
//    day2.puzzle1()
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

        reports.mapIndexed { reportNo, report ->
            val diff = isSafe(reportNo, report)
            for (i in diff.indices) {
                if(diff[i] == 0) return@mapIndexed report.filterIndexed() { index, _ -> index != (i + 1)}
                else if(diff[i].sign != diff.getOrElse(i+1) { _ -> diff[i] }.sign) return@mapIndexed report.filterIndexed { index, _ -> index != (i+1) }
            }
            return@mapIndexed report
        }.mapIndexed{ index, adjustedReport -> isSafe(index, adjustedReport) }.count() { abs(it.sum()) == it.size }.let { println(it) }

    }

    fun puzzle1() {
        this.parseInput(Day2::class.java.getResource("/Day2.txt")?.readText()!!)
        reports.mapIndexed { index, report ->  isSafe(index,report) }.count { abs(it.sum()) == it.size }.let { println(it) }
    }

    fun puzzle2() {
        this.parseInput(Day2::class.java.getResource("/Day2.txt")?.readText()!!)
        val temp = reports.mapIndexed { reportNo, report ->
            val diff = isSafe(reportNo, report)
            for (i in diff.indices) {
                if(diff[i] == 0) return@mapIndexed report.filterIndexed() { index, _ -> index != i }
                else if(diff[i].sign != diff.getOrElse(i+1) { _ -> diff[i] }.sign) return@mapIndexed report.filterIndexed() { index, _ -> index != i+1 }
            }
            return@mapIndexed report
        }.mapIndexed{ index, adjustedReport ->
            println(reports[index])
            println(adjustedReport)
            val diffs = isSafe(index, adjustedReport)
            println(diffs)
            diffs
        }

        println(temp.count { abs(it.sum()) == it.size })

    }

    private fun isSafe(reportNo: Int, report: List<Long>) : List<Int> {
        return report
            .mapIndexed {index, value -> value - report[max(0, index - 1)] }
            .filterIndexed {index, _ -> index != 0}
            .map { it.sign * ( if( abs(it) in 1 .. 3) 1 else 0) }
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