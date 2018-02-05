package board

import board.Direction.*

/**
 * Represents the position of a cell in the game board. The coordinates are 1-based.
 */
data class Cell(val row: Int, val col: Int)

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

fun Direction.reversed() = when (this) {
    UP -> DOWN
    DOWN -> UP
    RIGHT -> LEFT
    LEFT -> RIGHT
}

/*
Represents a square game board, with a value associated with each cell.
It provides functional style API to manipulate the values (any, all, etc.).
 */
interface GameBoard<T> {
    val size: Int

    /**
     * Returns the value at the given position in the board.
     */
    operator fun get(cell: Cell): T?

    /**
     * Updates the value at the given position in the board.
     */
    operator fun set(cell: Cell, value: T?)

    /**
     * Returns the value at the given coordinates in the board (1-based).
     */
    operator fun get(row: Int, col: Int): T?

    /**
     * Updates the value at the given [row] and [col] in the board (1-based).
     */
    operator fun set(row: Int, col: Int, value: T?)

    operator fun contains(value: T): Boolean

    fun getAllCells(): Collection<Cell>

    fun getRow(row: Int, colRange: IntProgression): List<Cell>
    fun getColumn(rowRange: IntProgression, col: Int): List<Cell>

    /**
     * Returns the list of cells for which the value matches the given [predicate].
     */
    fun filter(predicate: (T?) -> Boolean): Collection<Cell>

    /**
     * Returns the first cell for which the value matches the given [predicate].
     */
    fun find(predicate: (T?) -> Boolean): Cell?

    /**
     * Checks if the value for any cell matches the given [predicate].
     */
    fun any(predicate: (T?) -> Boolean): Boolean

    /**
     * Checks if the values for all cells in the board match the given [predicate].
     */
    fun all(predicate: (T?) -> Boolean): Boolean

    /**
     * Returns the coordinates of the cell adjacent to the given cell in the given direction, or null if the given
     * cell is at the border of the board.
     */
    fun Cell.getNeighbour(direction: Direction): Cell?
}
