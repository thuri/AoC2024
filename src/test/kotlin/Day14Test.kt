import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    private val example = """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
    """.trimIndent()

    private val borderMoves = """
        p=0,0 v=-1,0
        p=0,0 v=0,-1
        p=0,0 v=-1,-1
        p=10,0 v=1,0
        p=10,0 v=0,-1
        p=10,0 v=1,-1
        p=0,6 v=-1,0
        p=0,6 v=0,1
        p=0,6 v=-1,1
        p=10,6 v=1,0
        p=10,6 v=0,1
        p=10,6 v=1,1
    """.trimIndent()

    private val input = Day14::class.java.getResource("/Day14.txt")!!.readText()

    @Test
    fun testMove() {
//        p=0,4 v=3,-3
        val day = Day14(example, 11, 7)
        day.robots[0].move()
        assertEquals(Point(3,1), day.robots[0].position)
        day.robots[0].move()
        assertEquals(Point(6,5), day.robots[0].position)
        day.robots[0].move()
        assertEquals(Point(9,2), day.robots[0].position)
        day.robots[0].move()
        assertEquals(Point(1,6), day.robots[0].position)
    }

    @Test
    fun testMoveExample() {
//        p=2,4 v=2,-3
        val i = 10
        val day = Day14(example, 11, 7)
        assertEquals(Point(2,4), day.robots[i].position)
        day.robots[i].move()
        assertEquals(Point(4,1), day.robots[i].position)
        day.robots[i].move()
        assertEquals(Point(6,5), day.robots[i].position)
        day.robots[i].move()
        assertEquals(Point(8,2), day.robots[i].position)
        day.robots[i].move()
        assertEquals(Point(10,6), day.robots[i].position)
        day.robots[i].move()
        assertEquals(Point(1,3), day.robots[i].position)
    }

    @Test
    fun testMoveBorders() {
        var index=0;
        val day = Day14(borderMoves, 11, 7)
        day.robots.forEach { it.move() }
        assertEquals(Point(10,0), day.robots[index++].position)
        assertEquals(Point(0,6), day.robots[index++].position)
        assertEquals(Point(10,6), day.robots[index++].position)

        assertEquals(Point(0,0), day.robots[index++].position)
        assertEquals(Point(10,6), day.robots[index++].position)
        assertEquals(Point(0,6), day.robots[index++].position)

        assertEquals(Point(10,6), day.robots[index++].position)
        assertEquals(Point(0,0), day.robots[index++].position)
        assertEquals(Point(10,0), day.robots[index++].position)

        assertEquals(Point(0,6), day.robots[index++].position)
        assertEquals(Point(10,0), day.robots[index++].position)
        assertEquals(Point(0,0), day.robots[index++].position)
    }

    @Test
    fun example() {
        assertEquals(12, Day14(example, 11, 7).solve())
    }

    @Test
    fun puzzle() {
        assertEquals(225_810_288, Day14(input, 101, 103).solve())
    }

    @Test
    fun puzzle2() {
        assertEquals(-1, Day14(input, 101, 103).solve2())
    }

    @Test
    fun checkIsSymmetric() {
        val t = """
            p=5,0 v=-1,0
            p=4,1 v=-1,0
            p=6,1 v=-1,0
            p=3,2 v=-1,0
            p=4,2 v=-1,0
            p=7,2 v=-1,0
            p=6,2 v=-1,0
            p=2,3 v=-1,0
            p=8,3 v=-1,0
            p=1,4 v=-1,0
            p=9,4 v=-1,0
            p=0,5 v=-1,0
            p=10,5 v=-1,0
        """.trimIndent()

        assertTrue(Day14(t, 11,7).isSymmetric())
    }
}