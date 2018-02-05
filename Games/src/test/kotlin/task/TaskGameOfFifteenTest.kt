package task

import board.Game
import tests.AbstractTestCountParity
import tests.AbstractTestGameOfFifteen

class TaskTestCountParity: AbstractTestCountParity() {
    override fun countParity(permutation: List<Int>): Boolean = task.countParity(permutation)
}

class TaskTestGameOfFifteen : AbstractTestGameOfFifteen() {
    override fun newGame(): Game = task.newGameOfFifteen()
}