import Day10.Companion.WEST
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class Day15Test {

    private val exampleRoom = """
        ##########
        #..O..O.O#
        #......O.#
        #.OO..O.O#
        #..O@..O.#
        #O#..O...#
        #O..O..O.#
        #.OO.O.OO#
        #....O...#
        ##########
    """.trimIndent()

//    ##########
//    #..O..O.O#  <
//    #......O.#
//    #.OO..O.O#
//    #.O@...O.#
//    #O#..O...#
//    #O..O..O.#
//    #.OO.O.OO#
//    #....O...#
//    ##########

    private val exampleMovements = """
        <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
        vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
        ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
        <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
        ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
        ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
        >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
        <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
        ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
        v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
    """.trimIndent()

    @Test
    fun example1() {
        assertEquals(10092, Day15(exampleRoom, exampleMovements).solve())
    }

    private val roomInput = Day15::class.java.getResource("/Day15_warehouse.txt")!!.readText()
    private val directionsInput = Day15::class.java.getResource("/Day15_directions.txt")!!.readText()

    @Test
    fun puzzle1() {
        assertEquals(1413675, Day15(roomInput, directionsInput).solve())
    }

    @Test
    fun moveRobot() {
        val day15 = Day15(exampleRoom, exampleMovements)
        day15.room.moveRobot(WEST)
        assertSame(day15.room.robot, day15.room.roomContent[4][3])
        assertEquals(day15.room.robot.coord, Point(3,4))
        assertEquals(day15.room.roomContent[4][4].type, '.')
        assertEquals(day15.room.roomContent[4][2].type, 'O')
    }
}