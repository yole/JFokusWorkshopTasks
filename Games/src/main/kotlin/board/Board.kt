package board

import board.Direction.*

data class Cell(val i: Int, val j: Int)

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

/*
Represents a square game board, with a value associated with each cell.
It provides functional style API to manipulate the values (any, all, etc.).
 */
interface GameBoard<T> {
    val size: Int

    operator fun get(cell: Cell): T?
    operator fun set(cell: Cell, value: T?)

    operator fun get(i: Int, j: Int): T?
    operator fun set(i: Int, j: Int, value: T?)

    operator fun contains(value: T): Boolean

    fun getAllCells(): Collection<Cell>
    fun getRow(i: Int, jRange: IntProgression): List<Cell>
    fun getColumn(iRange: IntProgression, j: Int): List<Cell>

    fun filter(predicate: (T?) -> Boolean): Collection<Cell>
    fun find(predicate: (T?) -> Boolean): Cell?
    fun any(predicate: (T?) -> Boolean): Boolean
    fun all(predicate: (T?) -> Boolean): Boolean

    fun Cell.getNeighbour(direction: Direction): Cell?
}

fun Direction.reversed() = when (this) {
    UP -> DOWN
    DOWN -> UP
    RIGHT -> LEFT
    LEFT -> RIGHT
}