class Day11(example: String) {

    private var stones : MutableMap<Long,Long> =
        Regex("""(\d+)""")
        .findAll(example)
        .map { match -> match.groupValues[0].toLong()}.associateWith { 1L }
        .toMutableMap()

    fun solve(blinks : Int): Long {

        for(blink in 1 .. blinks) {
            val cache = mutableMapOf<Long, Long>()

            stones.forEach { (stone, counts) ->
                nextStones(stone).forEach{ nextStone ->
                    cache[nextStone] = cache.getOrDefault(nextStone, 0) + counts
                }
            }
            stones = cache
        }

        println(stones.size)
        stones.entries.take(10).forEach { println(it) }

        return stones.entries.sumOf { it.value }
    }

    private fun nextStones(
        stone: Long
    ): List<Long> {
        val stringStone = stone.toString()
        val digits = stringStone.length.toLong()
        if (stone == 0L) {
            return listOf(1L)
        } else if (digits % 2L == 0L) {
            val middle = (digits / 2).toInt()
            return listOf(
                stringStone.substring(0, middle).toLong(),
                stringStone.substring(middle).toLong()
            )
        } else {
            return listOf(stone * 2024L)
        }
    }
}