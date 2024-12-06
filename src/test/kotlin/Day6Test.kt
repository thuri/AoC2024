import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day6Test {

    private val day6 = Day6()

    @Test
    fun example() {
        val maze = toCharMatrix("""
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """.trimIndent())

        val result = day6.walk(maze)
        assertEquals(41, result.first)
        assertEquals(6, result.second)
    }

    @Test
    fun puzzle() {
        val maze = toCharMatrix(Day6::class.java.getResource("/Day6.txt")!!.readText())

        val result = day6.walk(maze)
        assertEquals(4515, result.first)
        assertEquals(-1, result.second) //360 too low
    }

    companion object {
        fun toCharMatrix(input : String) : Array<Array<Char>> {
            return input.lines().map { line -> line.toCharArray().toTypedArray() }.toTypedArray()
        }
    }
}