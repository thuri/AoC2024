fun main() {
    val day4 = Day4()
    day4.example1()
    day4.puzzle1()
    day4.example2()
    day4.puzzle2()
}

class Day4 {

    val exampleInput = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX""".trimIndent()

    fun example1() {
        findXmas(exampleInput).let(::println)
    }

    fun puzzle1() {
        findXmas(Day4::class.java.getResource("/Day4.txt")!!.readText()).let (::println )
    }

    fun example2() {
        findXXmas(exampleInput).let(::println)
    }

    fun puzzle2() {
        findXXmas(Day4::class.java.getResource("/Day4.txt")!!.readText()).let (::println )
    }

    private fun findXXmas(input: String) : Int {

        /* -1,-1       1,-1
                  A
          -1, 1       1,1
        */

        val crosses = listOf(
            mapOf(Pair(-1,-1) to 'M', Pair( 1, 1) to 'S'),
            mapOf(Pair(-1, 1) to 'M', Pair( 1,-1) to 'S'),
            mapOf(Pair( 1,-1) to 'M', Pair(-1, 1) to 'S'),
            mapOf(Pair( 1, 1) to 'M', Pair(-1,-1) to 'S'),
        )

        var counter = 0;
        val matrix = input.lines().map { line -> line.toCharArray() }.toTypedArray()
        for(row in matrix.indices) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == 'A') {
                    var crossCount = 0
                    for( c in crosses) {
                        crossCount += if(c.asSequence()
                            .fold(true) { acc, entry ->
                                   acc
                                && matrix.safeGet(row + entry.key.second).safeGet(col + entry.key.first) == entry.value
                            }) 1 else 0
                    }
                    if(crossCount == 2) counter++
                }
            }
        }
        return counter
    }

    private fun Array<CharArray>.safeGet(index : Int) :CharArray {
        return this.getOrElse(index){_-> CharArray(0)}
    }

    private fun CharArray.safeGet(index : Int) : Char{
        return this.getOrElse(index){_->'.'}
    }

    private fun findXmas(input: String) : Int {

        var counter = 0;
        val matrix = input.lines().map { line -> line.toCharArray() }.toTypedArray()
        for(row in matrix.indices) {
            for (col in matrix[row].indices) {
                if(matrix[row][col] == 'X') {
                    // horizontal
                    counter += if(isXmas(matrix, intArrayOf(row,row,row,row), intArrayOf(col,col+1,col+2,col+3))) 1 else 0;
                    // horizontal reversed
                    counter += if(isXmas(matrix, intArrayOf(row,row,row,row), intArrayOf(col,col-1,col-2,col-3))) 1 else 0;
                    // vertical
                    counter += if(isXmas(matrix, intArrayOf(row,row+1,row+2,row+3), intArrayOf(col,col,col,col))) 1 else 0;
                    // vertical reversed
                    counter += if(isXmas(matrix, intArrayOf(row,row-1,row-2,row-3), intArrayOf(col,col,col,col))) 1 else 0;
                    // diagonal right up
                    counter += if(isXmas(matrix, intArrayOf(row,row-1,row-2,row-3), intArrayOf(col,col+1,col+2,col+3))) 1 else 0;
                    // diagonal right down
                    counter += if(isXmas(matrix, intArrayOf(row,row+1,row+2,row+3), intArrayOf(col,col+1,col+2,col+3))) 1 else 0;
                    // diagonal left up
                    counter += if(isXmas(matrix, intArrayOf(row,row-1,row-2,row-3), intArrayOf(col,col-1,col-2,col-3))) 1 else 0;
                    // diagonal left down
                    counter += if(isXmas(matrix, intArrayOf(row,row+1,row+2,row+3), intArrayOf(col,col-1,col-2,col-3))) 1 else 0;
                }
            }
        }

        return counter
    }

    private fun isXmas(matrix: Array<CharArray>, rows : IntArray, cols : IntArray) : Boolean {
        val y = rows.iterator()
        val x = cols.iterator()
        return     matrix.safeGet(y.next()).safeGet(x.next()) == 'X'
                && matrix.safeGet(y.next()).safeGet(x.next()) == 'M'
                && matrix.safeGet(y.next()).safeGet(x.next()) == 'A'
                && matrix.safeGet(y.next()).safeGet(x.next()) == 'S'
    }

}