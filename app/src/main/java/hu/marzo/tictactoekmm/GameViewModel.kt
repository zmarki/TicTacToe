package hu.marzo.tictactoekmm

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.marzo.tictactoekmm.models.Board
import hu.marzo.tictactoekmm.models.BoardState
import hu.marzo.tictactoekmm.models.Position
import hu.marzo.tictactoekmm.models.PositionState

class GameViewModel : ViewModel() {
    private var board: Board = Board()
    val liveBoard = MutableLiveData(board)


    fun updateBoard() {
        liveBoard.value = board
    }


    /**
     * Position clicked by user
     *
     * @param position: The button clicked
     */
    fun positionClicked(position: Position) {
        if (board.getState(position) == PositionState.Empty && board.boardState == BoardState.INCOMPLETE) {
            board.setState(position, PositionState.Circle)
            updateBoard()
            if (board.boardState == BoardState.INCOMPLETE) {
                randomTurn()
            }
        }
    }

    /**
     * Reset the board
     */
    fun resetBoard() {
        board.resetBoard()
        updateBoard()
    }

    /**
     * Computer's random step
     */
    private fun randomTurn() {
        do {
            val pos = Position.values().random()
            val placedSuccessfully = board.setState(pos, PositionState.Cross)
        } while (!placedSuccessfully)

        updateBoard()
    }


}