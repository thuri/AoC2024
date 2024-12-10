class Day10 (input :  String){

    private val heightMap : Array<Array<Int>>

    init {
        heightMap = input.lines().map { line ->
            line.toCharArray().map { it.digitToInt() }.toTypedArray()
        }.toTypedArray()
    }

    fun solve1() : Long {
        return heightMap.mapIndexed{ y, row ->
            row.mapIndexed{ x, digit ->
                val currentPosition = Point(x,y)
                if(digit == 0) {
                    TrailFinder(this.heightMap).findPeaks(currentPosition)
//                    .let {
//                        println("$currentPosition = $it")
//                        it
//                    }
                } else {
                    0
                }
            }.sum()
        }.sum()
    }

    private class TrailFinder(private val heightMap : Array<Array<Int>>) {

        fun findPeaks(start : Point, visited: MutableSet<Point> = mutableSetOf()) : Long{
            var peakCount = 0L;
            if(heightMap[start] == 9) {
                visited.add(start)
                return 1
            }

            val potentialRoutes = ArrayDeque(
                directions
                .map { direction -> start + direction }
                .filter { next -> this.heightMap[next] != null}
                .filter { next -> this.heightMap[next] == (this.heightMap[start]?.plus(1)) }
                .filter { next -> !visited.contains(next)}
            )

            while(potentialRoutes.isNotEmpty()) {
                peakCount += findPeaks(potentialRoutes.removeFirst(), visited)
            }

            return peakCount
        }
    }

    companion object {

        operator fun Array<Array<Int>>.get(coordinats : Point) : Int? {
            return this.getOrElse(coordinats.second) { null }?.getOrElse(coordinats.first) { null }
        }

        operator fun Point.plus(vector : Vector) : Point =
            Point(this.first + vector.first, this.second + vector.second)

        private val NORTH = Vector(0, -1)
        private val EAST  = Vector(1,  0)
        private val SOUTH = Vector(0,  1)
        private val WEST  = Vector(-1, 0)

        private val directions = listOf(NORTH, EAST, SOUTH, WEST)
    }
}