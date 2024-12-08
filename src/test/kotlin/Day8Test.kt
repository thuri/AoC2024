import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    private val exampleInput = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent()

    private val input = Day8::class.java.getResource("/Day8.txt")!!.readText()

    @Test
    fun example1() {
        val day8 = Day8(exampleInput)
        assertEquals(14, day8.solve())
    }

    @Test
    fun puzzle1() {
        val day8 = Day8(input)
        assertEquals(371, day8.solve())
    }

    @Test
    fun example2() {
        val day8 = Day8(exampleInput)
        assertEquals(34, day8.solve2())
    }

    @Test
    fun puzzle2() {
        val day8 = Day8(input)
        assertEquals(1229, day8.solve2())
    }

    val t = """          
    ......#....#         ##....#....#
    ...#....0...         .#.#....0...
    ....#0....#.         ..#.#0....#.
    ..#....0....         ..##...0....
    ....0....#..         ....0....#..
    .#....A.....         .#...#A....#
    ...#........         ...#..#.....
    #......#....         #....#.#....
    ........A...         ..#.....A...
    .........A..         ....#....A..
    ..........#.         .#........#.
    ..........#.         ...#......##
    """
}