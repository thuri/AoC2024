import Day12.Companion.left
import Day12.Companion.right
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

/*      0123456789
      0         FF
      1          F
      2 VVRRRC FFF
      3 VVRCCC FFF
      4 VVVVCJJ F

 */


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
//    regionPlots.filter { it.borders.size > 0 }.map { "${it.plantType} ${it.coord} - ${it.borders}" }
    private val example2 = """
        AAAAAA
        AAABBA
        AAABBA
        ABBAAA
        ABBAAA
        AAAAAA
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

    @Test
    fun example2() {
        assertEquals(1206, Day12(example).solve2())
    }

    @Test
    fun puzzle2() {
        assertEquals(-1, Day12(input).solve2())
        // 809810 too low
    }

    @Test
    fun example2a() {
        assertEquals(368, Day12(example2).solve2())
    }

    @Test
    fun testRotatingLeft() {
        assertEquals(Vector(1, 0), Vector(0, 1).left())
        assertEquals(Vector(0,-1), Vector(1, 0).left())
        assertEquals(Vector(-1,0), Vector(0,-1).left())
        assertEquals(Vector(0, 1), Vector(-1,0).left())
    }

    @Test
    fun testRotatingRight() {
        assertEquals(Vector(-1,0), Vector(0, 1).right())
        assertEquals(Vector(0, 1), Vector(1, 0).right())
        assertEquals(Vector(1, 0), Vector(0,-1).right())
        assertEquals(Vector(0,-1), Vector(-1,0).right())
    }
}