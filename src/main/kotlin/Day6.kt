typealias Point = Pair<Int,Int>

class Day6() {

    private fun findStart(maze : Array<Array<Char>>) : Pair<Point,Point>{
        var position : Pair<Int,Int> = Pair(0,0)
        val direction = Pair(0,-1)
        for(y in maze.indices) {
            for (x in maze.indices) {
                if(maze[y][x] == '^') {
                    position = Pair(x,y);
                    break
                }
            }
        }
        return position to direction
    }

    private fun signForDirection(direction: Point) : Char =
        when(direction) {
            Point(0,-1) /*UP*/    -> '1'
            Point(0, 1) /*DOWN*/  -> '|'
            Point( 1,0) /*RIGHT*/ -> '~'
            Point(-1,0) /*LEFT*/  -> '-'
            else -> '.'
        }

    fun walk(maze : String) = walk(toCharMatrix(maze))

    private fun walk(maze : Array<Array<Char>>): Set<Pair<Point,Point>> {

        val start = findStart(maze)

        var position = start.first
        var direction = start.second

        val visited = mutableSetOf(position to direction)
        while(true) {

            val currentSign = maze[position.second][position.first]
            if(currentSign == signForDirection(direction))
                throw LoopException()
            maze[position.second][position.first] = signForDirection(direction)

            if(!listOf('^','|','1','-','~').contains(currentSign)) {
                visited.add(position to direction)
            }

            var next = position + direction
            if (next.second < 0 || next.first < 0 || next.second >= maze.size || next.first >= maze[0].size) break

            if (maze[next.second][next.first] == '#') {
//                println("rotate at $position")
                direction = rotateRight(direction)
                next = position + direction
            }

            position = next
//            println("move to $position [${visited.size}]")
        }

        return visited;
    }

    fun countLoops(mazeInput : String) : Int {

        val maze = toCharMatrix(mazeInput)
        val start = findStart(maze)
        val way = walk(maze).toMutableSet().apply {
            remove(start)
        }

        var loopCount = 0
        way.forEach { waypoint ->
            val loopMaze = toCharMatrix(mazeInput)
            loopMaze[waypoint.first.second][waypoint.first.first] = '#'
            try {
                Day6().walk(loopMaze)
            } catch (e : LoopException) {
                loopCount++
            }
        }

        return loopCount
    }

    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(this.first + other.first, this.second + other.second)
    }

    private fun rotateRight(v : Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(v.first * 0 - v.second * 1, v.first * 1 + v.second * 0)
    }

    fun toCharMatrix(input : String) : Array<Array<Char>> {
        return input.lines().map { line -> line.toCharArray().toTypedArray() }.toTypedArray()
    }
}

class LoopException : RuntimeException() {

}