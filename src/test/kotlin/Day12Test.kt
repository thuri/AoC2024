import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

    private val example ="""
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """.trimIndent()

    private val input = Day12::class.java.getResource("/Day12.txt")!!.readText()

    @Test
    fun example1() {
        assertEquals(1930, Day12(example).solve1())
    }

    @Test
    fun puzzle1() {
        assertEquals(1375574, Day12(input).solve1())
    }
}