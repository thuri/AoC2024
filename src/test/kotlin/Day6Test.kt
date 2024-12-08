import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day6Test {

    private val exampleMaze = """
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
        """.trimIndent()

    private val maze = Day6::class.java.getResource("/Day6.txt")!!.readText()

    @Test
    fun example1() {
        val day = Day6()
        assertEquals(41, day.walk(exampleMaze).map { it.first }.toSet().size )
    }

    @Test
    fun puzzle1() {
        val day = Day6()
        assertEquals(4515, day.walk(maze).map { it.first }.toSet().size)
    }

    @Test
    fun example2() {
        val day = Day6()
        assertEquals(6,day.countLoops(exampleMaze))
    }

    @Test
    fun puzzle2() {
        val day = Day6()
        assertEquals(1309, day.countLoops(maze))
    }
}