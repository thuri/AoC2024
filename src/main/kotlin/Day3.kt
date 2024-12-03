fun main() {

    val day3 = Day3()
    day3.example()
    day3.puzzle1()
    day3.exampleP2()
    day3.puzzle2()
}

class Day3 {

    val mul = { a: Int, b : Int -> a * b }

    val instructions = mutableListOf<Pair<Int, Int>>()
    val instructions2 = mutableListOf<String>()
    val instructionRegex = """mul\((\d+),(\d+)\)""".toRegex()
    val instructionRegex2 = """mul\((\d+),(\d+)\)|do\(\)|don't\(\)""".toRegex()

    private fun parseInstruction(input : String) {
        this.instructions.clear()
        input.lines().map {
            val matches = instructionRegex.findAll(it).iterator()
            while (matches.hasNext()) {
                val match = matches.next()
                instructions.add(Pair(match.groupValues[1].toInt(), match.groupValues[2].toInt()))
            }
        }
    }

    private fun parseInstruction2(input : String) {
        this.instructions.clear()
        this.instructions2.clear()
        var add = true
        input.lines().map {
            val matches = instructionRegex2.findAll(it).iterator()
            while (matches.hasNext()) {
                val match = matches.next()
                println(match.groupValues)
                if(match.groupValues[0].startsWith("don't")) {
                    add = false
                    continue
                } else if(match.groupValues[0].startsWith("do")) {
                    add = true
                    continue
                } else if (match.groupValues[0].startsWith("mul") && add) {
                    instructions.add(Pair(match.groupValues[1].toInt(), match.groupValues[2].toInt()))
                    continue
                }
            }
        }
    }


    fun example() {
        this.parseInstruction("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
        instructions.fold(0) { acc, pair -> acc + pair.first * pair.second }.let(::println)
    }

    fun puzzle1() {
        this.parseInstruction(Day3::class.java.getResource("/Day3.txt")?.readText()!!)
        instructions.fold(0) { acc, pair -> acc + pair.first * pair.second }.let(::println)
    }

    fun exampleP2() {
        this.parseInstruction2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
        instructions.fold(0) { acc, pair -> acc + pair.first * pair.second }.let(::println)
    }

    fun puzzle2() {
        this.parseInstruction2(Day3::class.java.getResource("/Day3.txt")?.readText()!!)
        instructions.fold(0) { acc, pair -> acc + pair.first * pair.second }.let(::println)
    }


}