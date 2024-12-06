class Day6 {

    companion object {
        private val RIGHT = Pair(1,0)
        private val DOWN  = Pair(0,1)
        private val UP    = Pair(0,-1)
        private val LEFT  = Pair(-1,0)

//        UP ---TURN---> RIGHT ---TURN---> DOWN ---TURN---> LEFT ---TURN---> UP
//        (0,-1)         (1,0)            (0,1)             (-1,0)

    }

    private fun signForDirection(direction: Pair<Int, Int>): Char {
        return when(direction) {
            UP-> '|'
            DOWN -> '1'
            LEFT -> '-'
            RIGHT -> '~'
            else -> '.'
        }
    }

    fun walk(maze: Array<Array<Char>>): Pair<Int,Int> {
        var position : Pair<Int,Int> = Pair(0,0)
        var direction = UP
        for(y in maze.indices) {
            for (x in maze.indices) {
                if(maze[y][x] == '^') {
                    position = Pair(x,y);
                    break
                }
            }
        }

        var counter = 1
        var obstacleCounter = 0
        while(true) {

            val currentSign = maze[position.second][position.first]
            if(!listOf('X','^','|','1','-','~').contains(currentSign)) {
                maze[position.second][position.first] = signForDirection(direction)
                counter += 1
            }

            var next = position + direction
            if(next.second >= maze.size || next.first >= maze[0].size) break

            if (maze[next.second][next.first] == '#') {
                println("rotate at $position")
                direction = rotateRight(direction)
                next = position + direction
            }
            else {
                val loopDirection = rotateRight(direction)
                var tempPoint = position
                while (true) {
                    tempPoint += loopDirection
                    if (tempPoint.second > 0 && tempPoint.second < maze.size && tempPoint.first > 0 && tempPoint.first < maze[0].size) {
                        val loopchar = maze[tempPoint.second][tempPoint.first]
                        if (loopchar == signForDirection(loopDirection)) {
                            obstacleCounter++
                            break
                        }
                        else if (loopchar == '#') break;
                    } else {
                        break;
                    }
                }
            }
                position = next
                println("move to $position [$counter]")

        }

        return Pair(counter,obstacleCounter);
    }

    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(this.first + other.first, this.second + other.second)
    }

    private fun rotateRight(v : Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(v.first * 0 - v.second * 1, v.first * 1 + v.second * 0)
    }
}