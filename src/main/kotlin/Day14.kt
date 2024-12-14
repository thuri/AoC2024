import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.abs


class Day14(example: String, val width: Int, val height: Int) {

    private val widthMiddle = width / 2
    private val heightMiddle = height / 2
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

        return this.robots
            .groupBy { it.quadrant() }
            .filter { it.key != null }
            .map { it.value.size }
            .reduce{ product, next -> product * next}
    }

    fun solve2() : Int {
        var counter = 0;
        val maxCount = 7_000
        val diff = 1000

        do {
            counter++

            val img =   if( (maxCount - diff) <= counter && counter < maxCount)
                            BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR)
                        else null
            robots.forEach {
                it.move();
                img?.setRGB(it.position.first, it.position.second, Color.GREEN.rgb)
            }

            img?.run {
                File("/tmp/day14/$counter.png").let { file ->
                    ImageIO.write(this, "png", file)
                }
            }

        } while(counter < maxCount)

        return counter;
    }

    fun isSymmetric(): Boolean {
        return this.robots.groupBy { it.position.second }
            .map { robotsPerLine ->
                val robotsPerHalf = robotsPerLine.value
                    .filter { it.position.first != widthMiddle }
                    .groupBy { it.position.first < widthMiddle }
                    .map { it.value }

                if (robotsPerHalf.size == 1) {
                    return false
                }
                if (robotsPerHalf.size == 2) {
                    if (robotsPerHalf[0].size != robotsPerHalf[1].size)
                        return false
                    else {
                        robotsPerHalf[0].map { leftRobot ->
                            robotsPerHalf[1].find { rightRobot ->
                                abs(width - leftRobot.position.first) == abs(width - rightRobot.position.first)
                            }
                        }
                        true
                    }
                } else {
                    true
                }
            }.reduce { result, next -> result && next }
    }

    inner class Robot (var position: Point, private val velocity : Vector) {

        fun quadrant() : Pair<Int,Int>? {

            if(this.position.first == widthMiddle || this.position.second == heightMiddle)
                return null

            return Pair(
                if(this.position.first < widthMiddle) 0 else 1,
                if(this.position.second < heightMiddle) 0 else 1
            )
        }

        fun move() {
            val pos = (this.position + velocity).let {
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