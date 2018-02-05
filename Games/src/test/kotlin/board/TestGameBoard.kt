package tests

import board.Cell
import board.GameBoard
import board.GameBoardImpl
import org.junit.Assert
import org.junit.Test

class AbstractTestGameBoard {
    fun <T> createGameBoard(size: Int): GameBoard<T> = GameBoardImpl(size)

    fun Cell?.print() = if (this != null) "($row, $col)" else ""

    fun Collection<Cell>.print() = joinToString { it.print() }

    @Test
    fun testAllCells() {
        val board = createGameBoard<Int>(2)
        val cells = board.getAllCells().sortedWith(compareBy<Cell> { it.row }.thenBy { it.col })
        Assert.assertEquals("(1, 1), (1, 2), (2, 1), (2, 2)", cells.print())
    }
    @Test
    fun testRow() {
        val board = createGameBoard<Int>(4)
        val row = board.getRow(1, 1..2)
        Assert.assertEquals("(1, 1), (1, 2)", row.print())
    }

    @Test
    fun testColumn() {
        val board = createGameBoard<Int>(4)
        val row = board.getColumn(1..2, 3)
        Assert.assertEquals("(1, 3), (2, 3)", row.print())
    }

    @Test
    fun testRowReversedRange() {
        val board = createGameBoard<Int>(4)
        val row = board.getRow(1, 4 downTo 1)
        Assert.assertEquals("(1, 4), (1, 3), (1, 2), (1, 1)", row.print())
    }

    @Test
    fun testColumnReversedRange() {
        val board = createGameBoard<Int>(4)
        val row = board.getColumn(2 downTo 1, 1)
        Assert.assertEquals("(2, 1), (1, 1)", row.print())
    }

    @Test
    fun testNeighbour() {
        with (createGameBoard<Int>(4)) {
            val cell = Cell(2, 2)
            org.junit.Assert.assertEquals("(1, 2)", cell.getNeighbour(board.Direction.UP).print())
            org.junit.Assert.assertEquals("(3, 2)", cell.getNeighbour(board.Direction.DOWN).print())
            org.junit.Assert.assertEquals("(2, 1)", cell.getNeighbour(board.Direction.LEFT).print())
            org.junit.Assert.assertEquals("(2, 3)", cell.getNeighbour(board.Direction.RIGHT).print())
        }
    }

    @Test
    fun testNullableNeighbour() {
        with (createGameBoard<Int>(4)) {
            val cell = Cell(1, 1)
            org.junit.Assert.assertNull(cell.getNeighbour(board.Direction.UP))
            org.junit.Assert.assertNull(cell.getNeighbour(board.Direction.LEFT))
            org.junit.Assert.assertEquals("(2, 1)", cell.getNeighbour(board.Direction.DOWN).print())
            org.junit.Assert.assertEquals("(1, 2)", cell.getNeighbour(board.Direction.RIGHT).print())
        }
    }


    @Test
    fun testGetAndSetElement() {
        val board = createGameBoard<Char>(2)
        board[1, 1] = 'a'
        Assert.assertEquals('a', board[1, 1])
    }

    @Test
    fun testContainsValue() {
        val board = createGameBoard<Char>(2)
        board[1, 1] = 'a'
        Assert.assertTrue('a' in board)
    }

    @Test
    fun testFilter() {
        val board = createGameBoard<Char>(2)
        board[1, 1] = 'a'
        board[1, 2] = 'b'
        val cells = board.filter { it == 'a' }
        Assert.assertEquals(1, cells.size)
        val cell = cells.first()
        Assert.assertEquals(1, cell.row)
        Assert.assertEquals(1, cell.col)
    }

    @Test
    fun testFind() {
        val board = createGameBoard<Char>(2)
        board[1, 1] = 'a'
        board[1, 2] = 'b'
        val cell = board.find { it == 'a' }
        Assert.assertEquals(1, cell?.row)
        Assert.assertEquals(1, cell?.col)
    }

    @Test
    fun testAll() {
        val board = createGameBoard<Char>(2)
        board[1, 1] = 'a'
        board[1, 2] = 'a'
        Assert.assertFalse(board.all { it == 'a' })
        board[2, 1] = 'a'
        board[2, 2] = 'a'
        Assert.assertTrue(board.all { it == 'a' })
    }

    @Test
    fun testAny() {
        val board = createGameBoard<Char>(2)
        board[1, 1] = 'a'
        board[1, 2] = 'b'
        Assert.assertTrue(board.any { it in 'a'..'b' })
        Assert.assertTrue(board.any { it == null })

    }
}