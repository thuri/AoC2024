import kotlin.math.max

typealias Vector = Pair<Int,Int>

class Day8(input: String) {

    private var antennaVectors : Map<Char,List<Vector>>
    private var WIDTH : Int = 0
    private var HEIGHT : Int = 0

    init {
        antennaVectors = input.lines().flatMapIndexed { y, line ->
            HEIGHT = max(HEIGHT, y)
            line.mapIndexed { x, char ->
                WIDTH = max(WIDTH, x)
                char to Vector(x,y)
            }.filter { p -> p.first != '.' }
        }.groupBy ({ it.first }, {it.second})
    }

    fun solve(): Int {
        val antinodes =antennaVectors.values.flatMap{ vectors ->
            return@flatMap vectors.flatMapIndexed{index, vector  ->
                val diffs = vectors.filterIndexed { i, _ -> i != index }.map { o ->
                    Vector(o.first - vector.first, o.second - vector.second)
                }
                val antinodes = diffs.map { diff ->
                    Point(vector.first + (2*diff.first), vector.second + (2*diff.second))
                }
                return@flatMapIndexed antinodes
            }.filter {
                it.first >= 0 && it.second >= 0 && it.first <= WIDTH && it.second <= HEIGHT
            }
        }.toSet()
        return antinodes.size
    }

    fun solve2(): Int {
        val antinodes =antennaVectors.values.flatMap{ vectors ->
            return@flatMap vectors.flatMapIndexed{index, vector  ->
                val diffs = vectors.filterIndexed { i, _ -> i != index }.map { o ->
                    Vector(o.first - vector.first, o.second - vector.second)
                }
                val antinodes = diffs.flatMap { diff ->
                    val points = mutableListOf<Point>()
                    var multiplier = 0
                    while(true) {
                        multiplier++
                        val nextPoint = Point(vector.first + (multiplier*diff.first), vector.second + (multiplier*diff.second))
                        if(nextPoint.first >= 0 && nextPoint.second >= 0 && nextPoint.first <= WIDTH && nextPoint.second <= HEIGHT)
                            points.add(nextPoint)
                        else
                            break
                    }
                    points
                }
                return@flatMapIndexed antinodes
            }.filter {
                it.first >= 0 && it.second >= 0 && it.first <= WIDTH && it.second <= HEIGHT
            }
        }.toSet()
        return antinodes.size
    }
}