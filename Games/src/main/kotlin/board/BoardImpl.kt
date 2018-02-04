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
        Direction.UP -> getCellOrNull(i - 1, j)
        Direction.DOWN -> getCellOrNull(i + 1, j)
        Direction.RIGHT -> getCellOrNull(i, j + 1)
        Direction.LEFT -> getCellOrNull(i, j - 1)
    }

    private fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i in 1..size && j in 1..size) Cell(i, j) else null
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.map { j -> Cell(i, j) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.map { i -> Cell(i, j) }
    }

    override operator fun get(i: Int, j: Int): T? = data[Cell(i, j)]

    override operator fun set(i: Int, j: Int, value: T?) {
        data[Cell(i, j)] = value
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