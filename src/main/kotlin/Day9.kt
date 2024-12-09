import java.util.ArrayDeque

class Day9(example: String) {

    data class Block (var id : Int?, val index: Int) {
        override fun toString() = id?.toString() ?: "."
    }

    data class Space (val index:Int, val length: Int, val free: Boolean, val fileId : Int?, var firstBlock : Int = -1)

    private val blocks : List<Block>
    private val spaces : List<Space>

    init {
        var fileIdGenerator = 0
        spaces = example.mapIndexed { index, number ->
            val isFree = (index % 2) == 1
            val id: Int? = if(isFree) null else fileIdGenerator++
            Space(index = index, length = number.digitToInt(), free = isFree, fileId = id)
        }
        var blockIndex = 0
        blocks = spaces.flatMap { space ->
            space.firstBlock = blockIndex
            (0 ..<space.length).map {
                Block(id=space.fileId, blockIndex++)
            }
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

        return checksum()
    }

    private fun checksum(): Long = blocks.foldIndexed(0) { index, checksum, block -> checksum + (index * (block.id ?: 0)) }

    fun solve2() : Long {

        val fileSpaces = ArrayDeque(spaces.filter { !it.free }.reversed())
        val freeSpaces = spaces.filter { it.free }.toMutableList()

        while(fileSpaces.isNotEmpty()) {
            val file = fileSpaces.pop()

            val newSpaceIdx = freeSpaces.indexOfFirst { it.length >= file.length }
            if(newSpaceIdx == -1) continue
            val newSpace = freeSpaces[newSpaceIdx]

            if(newSpace.firstBlock >= file.firstBlock) continue

            (newSpace.firstBlock ..< (newSpace.firstBlock + file.length)).forEach { idx ->
                this.blocks[idx].id = file.fileId
            }
            (file.firstBlock ..< (file.firstBlock + file.length)).forEach { idx ->
                this.blocks[idx].id = null
            }

            if(newSpace.length > file.length) {
                freeSpaces[newSpaceIdx] = Space(
                    index = newSpace.index,
                    free = true,
                    length = newSpace.length - file.length,
                    fileId = null,
                    firstBlock = newSpace.firstBlock + file.length)
            } else {
                freeSpaces.removeAt(newSpaceIdx)
            }
        }

        println(this.blocks.mapIndexed { _, block -> "${ block.id?.toString() ?: '.' }" })
        println(this.blocks.size)
        return checksum()
    }
}