package hu.marzo.tictactoekmm.models

class Board(private val gameBoard: MutableMap<Position, PositionState> = mutableMapOf()) {
    fun getState(position: Position): PositionState {
        return gameBoard[position] ?: PositionState.Empty
    }

    /**
     * Position clicked by user
     *
     * @param position: The button clicked
     */
    fun setState(position: Position, state: PositionState): Boolean {
        if (gameBoard.containsKey(position)) {
            return false
        }
        gameBoard[position] = state
        return true
    }

    /**
     * Reset the board
     */
    fun resetBoard() {
        gameBoard.clear()
    }

    /**
     * Find the state of the board
     */
    val boardState: BoardState
        get() = when {
            //Winner Cross
            hasStateWon(PositionState.Cross) -> BoardState.CROSS_WIN
            //Winner Circle
            hasStateWon(PositionState.Circle) -> BoardState.CIRCLE_WIN
            //Incomplete
            gameBoard.size <9 -> BoardState.INCOMPLETE
            else -> BoardState.DRAW
        }

    private fun hasStateWon(state: PositionState): Boolean{
        fun testState(vararg position: Position) = position.all {
            gameBoard[it]== state
        }
        return testState(Position.BOTTOM_LEFT,Position.CENTER_LEFT,Position.TOP_LEFT) ||
                testState(Position.BOTTOM_RIGHT,Position.TOP_RIGHT,Position.CENTER_RIGHT) ||
                testState(Position.BOTTOM_CENTER,Position.CENTER_CENTER,Position.TOP_CENTER) ||
                testState(Position.TOP_LEFT,Position.TOP_RIGHT,Position.TOP_CENTER) ||
                testState(Position.BOTTOM_RIGHT,Position.BOTTOM_CENTER,Position.BOTTOM_LEFT) ||
                testState(Position.CENTER_LEFT,Position.CENTER_CENTER,Position.CENTER_RIGHT) ||
                testState(Position.BOTTOM_RIGHT,Position.CENTER_CENTER,Position.TOP_LEFT) ||
                testState(Position.BOTTOM_LEFT,Position.TOP_RIGHT,Position.CENTER_CENTER)


    }
}