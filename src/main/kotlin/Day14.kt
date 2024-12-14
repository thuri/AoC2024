class Day14(example: String, val width: Int, val height: Int) {

    val robots : List<Robot>

    init {

        val regex = Regex("""p=(\d+),(\d+) v=(-?)(\d+),(-?)(\d+)""")

        this.robots = example.lines().map { line ->
            regex.find(line)?.let {

                val signLeft = if(it.groupValues[3] == "-") -1 else 1
                val signRight = if(it.groupValues[5] == "-") -1 else 1

                Robot(
                    Point(it.groupValues[1].toInt(),it.groupValues[2].toInt()),
                    Vector(signLeft * it.groupValues[4].toInt(), signRight * it.groupValues[6].toInt())
                )
            }!!
        }
    }

    fun solve(): Int {
        repeat(100) {
            this.robots.forEach {
                it.move()
            }
        }

        this.robots.forEach {
            println("${it.position} ${it.quadrant()}")
        }

        return this.robots
            .groupBy { it.quadrant() }
            .filter { it.key != null }
            .map { println(it); it.value.size }
            .reduce{ product, next -> product * next}
    }

    inner class Robot (var position: Point, private val velocity : Vector) {

        fun quadrant() : Pair<Int,Int>? {

            if(this.position.first == (width / 2) || this.position.second == (height / 2))
                return null

            return Pair(
                if(this.position.first < (width / 2)) 0 else 1,
                if(this.position.second < (height / 2)) 0 else 1
            )
        }

        fun move() {
            val pos = ((this.position + velocity) as Point).let {
                Point(
                    (width + it.first) % width,
                    (height + it.second) % height
                )
            }
            this.position = pos
        }

        override fun toString(): String {
            return "p=${this.position} v=${this.velocity}"
        }
    }
}