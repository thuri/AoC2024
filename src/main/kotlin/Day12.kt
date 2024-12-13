class Day12(input: String) {

    private val regions = mutableSetOf<Region>()
    private val plots : List<List<Plot>> =
        input
            .lines()
            .mapIndexed { y, row ->
                row.mapIndexed { x, plantType ->
                    Plot(Point(x,y), plantType)
                }
            }

    init  {
        plots.forEachIndexed { y, row ->
            row.forEachIndexed { x, plot ->
                directions.forEach {direction ->
                    val neighbor = plots[Point(x,y) + direction]
                    if(plot.plantType == neighbor?.plantType) {
                        plot.region + neighbor.region
                    } else {
                        plot.borders.add(direction)
                    }
                }
            }
        }
    }

    inner class Region (private val regionPlots : Set<Plot>) {

        init {
            regions.add(this)
        }

        private fun borders() = regionPlots.sumOf { it.borders.size.toLong() }
        private fun area()    = regionPlots.size
        private fun sides(): Long {
            val west  = mutableSetOf<Int>()
            val east  = mutableSetOf<Int>()
            val north = mutableSetOf<Int>()
            val south = mutableSetOf<Int>()

            regionPlots.forEach { plot ->
                   plot.borders.forEach() { border ->
                       when(border) {
                           WEST -> west += plot.coord.first
                           EAST -> east += plot.coord.first
                           NORTH -> north += plot.coord.second
                           SOUTH -> south += plot.coord.second
                       }
                   }
            }

            return (west.size + east.size + north.size + south.size).toLong()
        }

        fun price() : Long = borders() * area()
        fun price2() = sides() * area()

        operator fun plus(other : Region) : Region {
            regions.remove(this)
            regions.remove(other)

            return Region(this.regionPlots + other.regionPlots).apply{

                this@Region.regionPlots.forEach { it.region = this@apply }
                other.regionPlots.forEach { it.region = this@apply }

                regions.add(this@apply)
            }
        }
    }

    inner class Plot(val coord: Point, val plantType: Char, var borders: MutableSet<Vector> = mutableSetOf()) {
        var region : Region = Region(setOf(this))
    }

    fun solve1(): Long {
        return regions.sumOf { it.price() }
    }

    fun solve2() : Long {
        return regions.sumOf { it.price2() }
    }

    companion object {

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