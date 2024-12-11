import kotlin.math.log10

class Day11(example: String) {

    private var stones : List<Long> =
        Regex("""(\d+)""")
        .findAll(example)
        .map { match -> match.groupValues[0].toLong()}.toMutableList()

    fun solve(blinks : Int): Int {

        for(blink in 1 .. blinks) {
            stones = stones.flatMap { stone ->
//                val digits = (log10(stone.toDouble()) + 1).toLong()
                val stringStone = stone.toString()
                val digits = stringStone.length.toLong()
                if(stone == 0L) {
                    listOf(1L)
                } else if(digits % 2L == 0L) {
                    val middle = (digits / 2).toInt()
                    // stone = a * 10^middle + b
                    listOf(stringStone.substring(0, middle).toLong(), stringStone.substring(middle).toLong())
                } else {
                    listOf(stone * 2024L)
                }
            }
        }

        return stones.size;
    }
}