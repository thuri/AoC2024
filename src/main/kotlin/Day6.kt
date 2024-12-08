typealias Point = Pair<Int,Int>

class Day6 {

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

    fun walk(maze : String): Set<Pair<Point, Point>> {
        val charMaze = toCharMatrix(maze)
        return walk(findStart(charMaze), charMaze)
    }

    private fun walk(start : Pair<Point,Point>, maze : Array<Array<Char>>): Set<Pair<Point,Point>> {

        var position = start.first
        var direction = start.second

        val visited = mutableSetOf<Pair<Point,Point>>()
        while(true) {

            if(visited.contains(Pair(position,direction)))
                throw LoopException()
            visited.add(position to direction)

            var next = position + direction
            if (next.second < 0 || next.first < 0 || next.second >= maze.size || next.first >= maze[0].size) {
                break
            }

            if (maze[next.second][next.first] == '#') {
                direction = rotateRight(direction)
                next = position + direction
            }
            position = next
        }

        return visited;
    }

    fun countLoops(mazeInput : String) : Int {

        val maze = toCharMatrix(mazeInput)
        val start = findStart(maze)
        val way = walk(start, maze).toMutableSet().apply {
            remove(start)
        }

        val newObstacles = mutableSetOf<Point>()
        way.forEach { waypoint ->
            val loopMaze = toCharMatrix(mazeInput)
            val obstacle = waypoint.first
            loopMaze[obstacle.second][obstacle.first] = '#'
            try {
                Day6().walk(start, loopMaze)
            } catch (e: LoopException) {
                newObstacles.add(obstacle)
            }
        }

        return newObstacles.distinct().size
    }

    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(this.first + other.first, this.second + other.second)
    }

    private fun rotateRight(v : Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(v.first * 0 - v.second * 1, v.first * 1 + v.second * 0)
    }

    private fun toCharMatrix(input : String) : Array<Array<Char>> {
        return input.lines().map { line -> line.toCharArray().toTypedArray() }.toTypedArray()
    }
}

class LoopException : RuntimeException()