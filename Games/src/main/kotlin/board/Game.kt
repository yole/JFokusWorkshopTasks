package board

/**
 * Implements the game logic for a game that is played on a square board filled with numbers.
 */
interface Game {
    /**
     * Creates the initial game state.
     */
    fun initialize()

    /**
     * Checks if the player has lost the game (no further moves are possible).
     */
    fun hasLost(): Boolean

    /**
     * Checks if the player has won the game.
     */
    fun hasWon(): Boolean

    /**
     * Processes the player's input, consisting of pressing the given arrow key.
     */
    fun processMove(direction: Direction)

    /**
     * Returns the number at the given position (1-based).
     */
    operator fun get(i: Int, j: Int): Int?
}
