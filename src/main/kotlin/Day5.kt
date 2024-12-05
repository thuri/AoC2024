fun main() {
    val day5 = Day5()
    day5.example1()
    day5.puzzle1()
}

class Day5 {

    fun example1() {
        val rules = parseRules("""
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
        """.trimIndent())
        val updatesReversed = parseUpdates("""
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent())

        sumOfCorrectMiddleNumbers(updatesReversed, rules)
    }

    fun puzzle1() {
        val rules = parseRules(Day5::class.java.getResource("/Day5.rules.txt")!!.readText())
        val updatesReversed = parseUpdates(Day5::class.java.getResource("/Day5.updates.txt")!!.readText())

        sumOfCorrectMiddleNumbers(updatesReversed, rules)
    }

    private fun sumOfCorrectMiddleNumbers(
        updatesReversed: List<List<Int>>,
        rules: Map<Int, List<Int>>
    ) {
        updatesReversed.filter { update ->
            var correct = true
            val seenPages = mutableListOf<Int>()
            for (page in update) {
                val allowedPages = rules[page] ?: listOf()
                correct = correct && (allowedPages.containsAll(seenPages))
                seenPages.add(page)
            }
    //            if(correct) println(update)
            return@filter correct
        }.sumOf { it[it.size / 2] }.let(::println)
    }

    private fun parseUpdates(updates: String, reversed : Boolean = true): List<List<Int>> {
        val regex = """(\d+)""".toRegex()
        return updates.lines().map { line -> regex.findAll(line)
                              .map { match -> match.groupValues[0].toInt()}
                              .toList()
                              .let { if(reversed) it.reversed() else it } }
    }

    private fun parseRules(rules: String): Map<Int,List<Int>> {
        val regex = """(\d+)\|(\d+)""".toRegex()
        return rules.lines().map { val result = regex.find(it)!!.groupValues ; Pair(result[1].toInt(), result[2].toInt()) }
            .groupBy({it.first},{it.second})
    }
}