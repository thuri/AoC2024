fun main() {
    val day4 = Day4()
    day4.example1()
    day4.puzzle1()
    day4.puzzle2()
}

class Day4 {

    fun example1() {

        val input = """
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

        findXmas(input).let(::println)
    }


    fun puzzle1() {
        findXmas(Day4::class.java.getResource("/Day4.txt")!!.readText()).let (::println )
    }

    fun puzzle2() {
        findXXmas(Day4::class.java.getResource("/Day4.txt")!!.readText()).let (::println )
    }

    private fun findXXmas(input: String) : Int {
        var counter = 0;
        val matrix = input.lines().map { line -> line.toCharArray() }.toTypedArray()
        for(row in matrix.indices) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == 'M') {

                }
            }
        }
        return counter
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
        return     matrix.getOrElse(y.next()) {_->CharArray(0) }.getOrElse(x.next()) {_->'.'} == 'X'
                && matrix.getOrElse(y.next()) {_->CharArray(0) }.getOrElse(x.next()) {_->'.'} == 'M'
                && matrix.getOrElse(y.next()) {_->CharArray(0) }.getOrElse(x.next()) {_->'.'} == 'A'
                && matrix.getOrElse(y.next()) {_->CharArray(0) }.getOrElse(x.next()) {_->'.'} == 'S'
    }

    private fun isXXmas(matrix: Array<CharArray>, startPosX: Int, startPosY : Int) : Boolean {

        return matrix.getOrElse(startPosY){ _->CharArray(0)}.getOrElse(startPosX) {_->'.'} == 'M'
            && matrix.getOrElse(startPosY){ _->CharArray(0)}.getOrElse(startPosX) {_->'.'} == 'X'
    }



}