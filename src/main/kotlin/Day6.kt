import kotlin.math.sin

class Day6 {

    companion object {
        private val RIGHT = Pair(1,0)
        private val DOWN  = Pair(0,1)
        private val UP    = Pair(0,-1)
        private val LEFT  = Pair(-1,0)

//        UP ---TURN---> RIGHT ---TURN---> DOWN ---TURN---> LEFT ---TURN---> UP
//        (0,-1)         (1,0)            (0,1)             (-1,0)

    }

    fun puzzle1(maze: Array<Array<Char>>): Int {
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
        while(true) {

            val currentSign = maze[position.second][position.first]
            if(currentSign != 'X' && currentSign != '^') {
                maze[position.second][position.first] = 'X'
                counter += 1
            }

            val next = position + direction
            if(next.second >= maze.size || next.first >= maze[0].size) break

            if (maze[next.second][next.first] == '#') {
                println("rotate at $position")
                direction = rotateRight(direction)
            } else {
                position = next
                println("move to $position [$counter]")
            }
        }

        return counter;
    }

    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(this.first + other.first, this.second + other.second)
    }

    private fun rotateRight(v : Pair<Int,Int>) : Pair<Int,Int>{
        return Pair(v.first * 0 - v.second * 1, v.first * 1 + v.second * 0)
    }
}