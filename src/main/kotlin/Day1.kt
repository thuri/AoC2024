import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

fun main() {
    val day1 = Day1()
    day1.puzzle1()
    day1.puzzle2()
}

class Day1 {

    private val list1 = mutableListOf<Long>()
    private val list2 = mutableListOf<Long>()

    init {
        val lineRegex = """(\d+)\s+(\d+)""".toRegex()
        Day1::class.java.getResource("/Day1.txt")?.readText()?.let { file ->
            file.lines().map { line ->
                val matches = lineRegex.find(line)
                if(matches != null) {
                    list1.add(matches.groupValues[1].toLong())
                    list2.add(matches.groupValues[2].toLong())
                }
            }
        }

    }

    fun puzzle1() {
        list1.sorted().zip(list2.sorted()).map { (a, b) -> abs(a-b) }.reduce(Long::plus).let(::println)
    }
    fun puzzle2() {
        val occurrences = list2.groupBy { it }.map { (k, v) -> k to v.size}.toMap()

        list1.map{ number -> (number * (occurrences[number] ?: 0))}
            .reduce(Long::plus)
            .let (::println)
    }
}

