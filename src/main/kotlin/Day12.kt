class Day12(input: String) {

    class Region (val plots : Set<Plot>) {

        init {
            regions.add(this)
        }

        private fun borders() = plots.sumOf { it.borders }
        private fun area() = plots.size

        fun price() : Long = borders() * area()

        operator fun plus(other : Region) : Region {
            regions.remove(this)
            regions.remove(other)

            return Region(this.plots + other.plots).apply{

                this@Region.plots.forEach { it.region = this@apply }
                other.plots.forEach { it.region = this@apply }

                regions.add(this@apply)
            }
        }
    }

    class Plot(val coord: Point, val plantType: Char, var borders: Long = 0) {
        var region : Region = Region(setOf(this))
    }

    private val plots : List<List<Plot>> =
        input
            .lines()
            .mapIndexed { y, row ->
                row.mapIndexed { x, plantType ->
                    Plot(Point(x,y), plantType)
                }
            }

    fun solve1(): Long {
        plots.forEachIndexed { y, row ->
            row.forEachIndexed { x, plot ->
                directions.forEach {direction ->
                    val neighbor = plots[Point(x,y) + direction]
                    if(plot.plantType == neighbor?.plantType) {
                        plot.region + neighbor.region
                    } else {
                        plot.borders++
                    }
                }
            }
        }

        return regions.sumOf { it.price() }
    }

    companion object {

        private val regions = mutableSetOf<Region>()

        operator fun List<List<Plot>>.get(corrdinates : Point) : Plot? {
            return this.getOrElse(corrdinates.second) { null }?.getOrElse(corrdinates.first) { null }
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