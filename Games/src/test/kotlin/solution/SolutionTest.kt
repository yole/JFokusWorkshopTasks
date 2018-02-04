package solutionTest

import board.Cell
import board.Direction
import board.Game
import board.GameBoard
import solution.*
import tests.*

class SolutionTestMoveAndMergeEqual : AbstractTestMoveAndMergeValues() {
    override fun <T : Any> moveAndMergeEqual(list: List<T?>, double: (T) -> T): List<T> =
            list.moveAndMergeEqual(double)
}

class SolutionTestAddRandomValue : AbstractTestAddRandomValue() {
    override fun addRandomValue(board: GameBoard<Int?>) = board.addRandomValue()
}

class SolutionTestMoveValuesInRowOrColumn: AbstractTestMoveValuesInRowOrColumn() {
    override fun moveValuesInRowOrColumn(gameBoard: GameBoard<Int?>, rowOrColumn: List<Cell>): Boolean =
            gameBoard.moveValuesInRowOrColumn(rowOrColumn)
}

class SolutionTestMoveValues: AbstractTestMoveValues() {
    override fun moveValues(board: GameBoard<Int?>, direction: Direction): Boolean =
            board.moveValues(direction)
}

class SolutionTestGame2048: AbstractTestGame2048() {
    override fun newGame(): Game = newGame2048()
}

class SolutionTestCountParity: AbstractTestCountParity() {
    override fun countParity(permutation: List<Int>): Boolean = solution.countParity(permutation)
}

class SolutionTestGameOfFifteen : AbstractTestGameOfFifteen() {
    override fun newGame(): Game = newGameOfFifteen()
}

