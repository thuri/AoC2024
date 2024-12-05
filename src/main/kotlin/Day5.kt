fun main() {
    val day5 = Day5()
    day5.example1()
    day5.puzzle1()
    day5.example2()
    day5.puzzle2()
}

class Day5 {

    private val exampleRules = parseRules("""
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
    private val exampleUpdates = parseUpdates("""
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent())

    fun example1() {
        sumOfCorrectMiddleNumbers(exampleUpdates, exampleRules)
    }

    fun puzzle1() {
        val rules = parseRules(Day5::class.java.getResource("/Day5.rules.txt")!!.readText())
        val updatesReversed = parseUpdates(Day5::class.java.getResource("/Day5.updates.txt")!!.readText())

        sumOfCorrectMiddleNumbers(updatesReversed, rules)
    }

    fun example2() {
        exampleUpdates.filter { update -> !correctUpdate(update, exampleRules) }
            .map { update -> update.sortedWith(CustomComparator(exampleRules)) }
            .sumOf { it[it.size / 2] }.let (::println)
    }

    fun puzzle2() {
        val rules = parseRules(Day5::class.java.getResource("/Day5.rules.txt")!!.readText())
        val updates = parseUpdates(Day5::class.java.getResource("/Day5.updates.txt")!!.readText())
        updates.filter { update -> !correctUpdate(update, rules) }
            .map { update -> update.sortedWith(CustomComparator(rules)) }
            .sumOf { it[it.size / 2] }.let (::println)
    }

    private fun sumOfCorrectMiddleNumbers(
        updatesReversed: List<List<Int>>,
        rules: Map<Int, List<Int>>
    ) {
        updatesReversed.filter { update -> correctUpdate(update, rules) }
        .sumOf { it[it.size / 2] }.let(::println)
    }

    private fun correctUpdate(
        update: List<Int>,
        rules: Map<Int, List<Int>>
    ): Boolean {
        var correct = true
        val seenPages = mutableListOf<Int>()
        for (page in update.reversed()) {
            val allowedPages = rules[page] ?: listOf()
            correct = correct && (allowedPages.containsAll(seenPages))
            seenPages.add(page)
        }
        return correct
    }

    private fun parseUpdates(updates: String): List<List<Int>> {
        val regex = """(\d+)""".toRegex()
        return updates.lines().map { line -> regex.findAll(line)
                              .map { match -> match.groupValues[0].toInt()}
                              .toList() }
    }

    private fun parseRules(rules: String): Map<Int,List<Int>> {
        val regex = """(\d+)\|(\d+)""".toRegex()
        return rules.lines().map { val result = regex.find(it)!!.groupValues ; Pair(result[1].toInt(), result[2].toInt()) }
            .groupBy({it.first},{it.second})
    }


    class CustomComparator (private val rules: Map<Int,List<Int>>): Comparator<Int> {

        override fun compare(o1: Int?, o2: Int?): Int {
            if(o1 == o2) return 0;
            if(this.rules[o1]?.contains(o2) ?: false) return -1
            if(this.rules[o2]?.contains(o1) ?: false) return 1
            return 0;
        }

    }
}