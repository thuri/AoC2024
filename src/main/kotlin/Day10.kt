class Day10 (input :  String){

    private val trailFinders : List<TrailFinder>

    init {
        val heightMap = input.lines().map { line ->
            line.toCharArray().map { it.digitToInt() }.toTypedArray()
        }.toTypedArray()

        trailFinders = heightMap.flatMapIndexed { y, row ->
            row.mapIndexed { x, digit ->
                if (digit == 0) TrailFinder(heightMap, Point(x, y)) else null
            }
            .filterNotNull()
        }
    }

    fun solve1() = trailFinders.sumOf { it.findPeaks() }

    fun solve2() = trailFinders.sumOf { it.findWays() }

    private class TrailFinder(private val heightMap : Array<Array<Int>>, val start: Point) {

        fun findPeaks(current : Point = start, visited: MutableSet<Point> = mutableSetOf()) : Long{

            if(heightMap[current] == 9) {
                visited.add(current)
                return 1
            }

            return directions
                .asSequence()
                .map { direction -> current + direction }
                .filter { next -> this.heightMap[next] != null }
                .filter { next -> this.heightMap[next] == (this.heightMap[current]?.plus(1)) }
                .filter { next -> !visited.contains(next) }
                .sumOf { next -> findPeaks(next, visited) }
        }

        fun findWays(current : Point = start, visited: MutableSet<Point> = mutableSetOf()) : Long {

            if(heightMap[current] == 9) return 1

            return directions
                .map { direction -> current + direction }
                .filter { next -> this.heightMap[next] != null}
                .filter { next -> this.heightMap[next] == (this.heightMap[current]?.plus(1)) }
                .sumOf { next -> findWays(next, visited) }
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