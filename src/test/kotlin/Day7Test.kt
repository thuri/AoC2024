import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    private val exampleInput = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    private val puzzleInput = Day7::class.java.getResource("/Day7.txt")!!.readText()

    @Test
    fun example1() {
        assertEquals(3749, Day7(exampleInput).solve1());
    }

    @Test
    fun puzzle1() {
        assertEquals(2664460013123, Day7(puzzleInput).solve1());
    }

    @Test
    fun example2() {
        assertEquals(11387, Day7(exampleInput).solve2());
    }

    @Test
    fun puzzle2() {
        assertEquals(-1, Day7(puzzleInput).solve2());
    }
}