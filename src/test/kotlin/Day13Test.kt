import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {

    private val example = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent()

    private val puzzle = Day13::class.java.getResource("/Day13.txt")!!.readText()

    @Test
    fun example1() {
        assertEquals(480, Day13(example).solve())
    }

    @Test
    fun puzzle1() {
        assertEquals(36870, Day13(puzzle).solve())
    }

    @Test
    fun puzzle2() {
        assertEquals(-1L, Day13(puzzle).solve2())
        // too low 72098849784658
        // too low 72099173844999
    }
}