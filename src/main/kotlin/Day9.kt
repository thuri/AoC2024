import java.util.ArrayDeque

class Day9(example: String) {

    data class Block (var id : Int?, val index: Int) {
        override fun toString() = id?.toString() ?: "."
    }

    private val blocks : List<Block>

    init {
        var idGenerator = 0
        var newIndex = 0
        blocks = example.flatMapIndexed { index, number ->
            val isFree = (index % 2) == 1
            val id: Int? = if(isFree) null else idGenerator++
            (0..<number.digitToInt()).map { Block(id = id, newIndex++) }
        }
    }

    fun solve(): Long {

        val fileBlocksFromEnd = ArrayDeque(this.blocks.filter { it.id != null }.reversed())

        blocks.forEach { block ->
            val currentTail = fileBlocksFromEnd.peek()
            if(block.id == null && block.index <= currentTail.index) {
                block.id = currentTail.id
                fileBlocksFromEnd.pop()
            } else if (block.index > currentTail.index) {
                block.id = null
            }
        }

        return blocks.foldIndexed(0) { index, checksum, block -> checksum + (index * (block.id ?: 0))}
    }
}