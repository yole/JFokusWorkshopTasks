package board

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

class GameBoardImpl<T>(override val size: Int) : GameBoard<T> {
    private val data = hashMapOf<Cell, T?>()

    init {
        for (i in 1..size) {
            for (j in 1..size) {
                data[Cell(i, j)] = null
            }
        }
    }

    override fun getAllCells(): Collection<Cell> {
        return data.keys
    }

    override fun Cell.getNeighbour(direction: Direction) = when (direction) {
        Direction.UP -> getCellOrNull(row - 1, col)
        Direction.DOWN -> getCellOrNull(row + 1, col)
        Direction.RIGHT -> getCellOrNull(row, col + 1)
        Direction.LEFT -> getCellOrNull(row, col - 1)
    }

    private fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i in 1..size && j in 1..size) Cell(i, j) else null
    }

    override fun getRow(row: Int, colRange: IntProgression): List<Cell> {
        return colRange.map { j -> Cell(row, j) }
    }

    override fun getColumn(rowRange: IntProgression, col: Int): List<Cell> {
        return rowRange.map { i -> Cell(i, col) }
    }

    override operator fun get(row: Int, col: Int): T? = data[Cell(row, col)]

    override operator fun set(row: Int, col: Int, value: T?) {
        data[Cell(row, col)] = value
    }

    override operator fun get(cell: Cell): T? = data[cell]

    override operator fun set(cell: Cell, value: T?) {
        data[cell] = value
    }

    override fun contains(value: T): Boolean = data.values.contains(value)

    override fun filter(predicate: (T?) -> Boolean): Set<Cell> =
            data.keys.filter { predicate(data[it]) }.toSet()

    override fun any(predicate: (T?) -> Boolean): Boolean =
            data.values.any { predicate(it) }

    override fun all(predicate: (T?) -> Boolean): Boolean =
            data.values.all { predicate(it) }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return data.keys.find { predicate(data[it]) }
    }
}