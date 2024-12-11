import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {

    val example = "125 17".trimIndent()

    val input = "30 71441 3784 580926 2 8122942 0 291"

    @Test
    fun example1() {
        assertEquals(55312, Day11(example).solve(25))
    }

    @Test
    fun puzzle1() {
        assertEquals(191690, Day11(input).solve(25))
    }

    @Test
    fun puzzle2() {
        assertEquals(228651922369703, Day11(input).solve(75))
    }
}