import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day6Test {

    private val day6 = Day6()

    @Test
    fun example1() {
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

        assertEquals(41, day6.puzzle1(maze))
    }

    @Test
    fun puzzle1() {
        val maze = toCharMatrix(Day6::class.java.getResource("/Day6.txt")!!.readText())

        assertEquals(4515, day6.puzzle1(maze))
    }

    companion object {
        fun toCharMatrix(input : String) : Array<Array<Char>> {
            return input.lines().map { line -> line.toCharArray().toTypedArray() }.toTypedArray()
        }
    }
}