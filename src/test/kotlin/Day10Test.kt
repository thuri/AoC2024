import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10Test {

    private val example = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    private val input = Day10::class.java.getResource("/Day10.txt")!!.readText()

    @Test
    fun example1() {
        assertEquals(36, Day10(example).solve1())
    }

    @Test
    fun puzzle1() {
        assertEquals(789, Day10(input).solve1())
    }
}