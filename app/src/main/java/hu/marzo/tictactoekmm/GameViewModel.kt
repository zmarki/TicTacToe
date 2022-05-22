package hu.marzo.tictactoekmm

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
    fun positionClicked(position: Position){
        board.setState(position, PositionState.Circle)
        updateBoard()
    }

    /**
     * Reset the board
     */
    fun resetBoard() {
        board.resetBoard()
        updateBoard()
    }



}