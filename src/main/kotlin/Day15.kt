import Day10.Companion.EAST
import Day10.Companion.NORTH
import Day10.Companion.SOUTH
import Day10.Companion.WEST

class Day15(roomInput: String, movesInput: String) {

    val room = Room(roomInput)
    private val directions = movesInput.lines().flatMap{ line ->
        line.toCharArray().map {
            when(it) {
                '<' -> WEST
                'v' -> SOUTH
                '>' -> EAST
                else -> NORTH
            }
        }
    }

    fun solve(): Long {
        directions.forEach {
            room.moveRobot(it)
//            println(it)
//            room.print()
        }
        return room.getGPSSum()
    }

    /*
     *  HELPERS
     */
    inner class Robot (coord: Point): Cell(type = '@', coord = coord)

    open inner class Cell(val type : Char, var coord : Point) {
        fun move(direction: Vector) : Boolean {

            if(this.type == '#') return false
            else if(this.type == '.') return true

            val neighborCoordinates = this.coord + direction
            val neighbor = room.roomContent[neighborCoordinates]

            val moved = neighbor?.move(direction) ?: false

            if(moved) {
                room.roomContent[neighborCoordinates] = this
                if(this.type == '@') {
                    room.roomContent[this.coord] = Cell('.', this.coord)
                }
                this.coord = neighborCoordinates
            }
            return moved;
        }
        fun getGPS() = coord.second * 100L + coord.first
    }

    inner class Room(exampleRoom : String) {

        lateinit var robot: Day15.Robot

        val roomContent: MutableList<MutableList<Day15.Cell>> = exampleRoom.lines()
        .mapIndexed { y, line ->
            line.toCharArray().mapIndexed { x, char ->
                when (char) {
                    'O' -> Cell('O', Point(x,y))
                    '#' -> Cell('#', Point(x,y))
                    '@' -> Robot(Point(x,y)).let { this.robot = it; it }
                    else ->  Cell('.', Point(x,y))
                }
            }.toMutableList()
        }.toMutableList()

        fun moveRobot(direction : Vector) {
            this.robot.move(direction)
        }

        fun getGPSSum() : Long {
            return this.roomContent.flatMap { lines ->
                lines.filter {it.type == 'O'}.map { cell ->
                    cell.getGPS()
                }
            }.sum()
        }

        fun print() {
            this.roomContent.forEach { line ->
                println (line.map { it.type }.toCharArray())
            }
        }
    }

    companion object {
        operator fun MutableList<MutableList<Cell>>.get(point : Point) =
            this.getOrNull(point.second)?.getOrNull(point.first)

        operator fun MutableList<MutableList<Cell>>.set(point : Point, cell: Cell) {
            this.getOrNull(point.second)?.set(point.first, cell)
        }
    }

}