package hu.marzo.tictactoekmm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import hu.marzo.tictactoekmm.databinding.GameFragmentBinding
import hu.marzo.tictactoekmm.models.Board
import hu.marzo.tictactoekmm.models.BoardState
import hu.marzo.tictactoekmm.models.Position
import java.util.*

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    private val gameViewModel: GameViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)


        gameViewModel.liveBoard.observe(this.viewLifecycleOwner, Observer {
            updateBoardCell(it)
            updateGameStatus(it.boardState)
        })
        clickButtons()

        return binding.root
    }


    private fun clickButtons() = with(binding) {
        ibtn0.setOnClickListener { gameViewModel.positionClicked(Position.TOP_LEFT) }
        ibtn1.setOnClickListener { gameViewModel.positionClicked(Position.TOP_CENTER) }
        ibtn2.setOnClickListener { gameViewModel.positionClicked(Position.TOP_RIGHT) }
        ibtn3.setOnClickListener { gameViewModel.positionClicked(Position.CENTER_LEFT) }
        ibtn4.setOnClickListener { gameViewModel.positionClicked(Position.CENTER_CENTER) }
        ibtn5.setOnClickListener { gameViewModel.positionClicked(Position.CENTER_RIGHT) }
        ibtn6.setOnClickListener { gameViewModel.positionClicked(Position.BOTTOM_LEFT) }
        ibtn7.setOnClickListener { gameViewModel.positionClicked(Position.BOTTOM_CENTER) }
        ibtn8.setOnClickListener { gameViewModel.positionClicked(Position.BOTTOM_RIGHT) }
    }

    private fun updateBoardCell(board: Board) {
        binding.ibtn0.setImageResource(board.getState(Position.TOP_LEFT).res)
        binding.ibtn1.setImageResource(board.getState(Position.TOP_CENTER).res)
        binding.ibtn2.setImageResource(board.getState(Position.TOP_RIGHT).res)
        binding.ibtn3.setImageResource(board.getState(Position.CENTER_LEFT).res)
        binding.ibtn4.setImageResource(board.getState(Position.CENTER_CENTER).res)
        binding.ibtn5.setImageResource(board.getState(Position.CENTER_RIGHT).res)
        binding.ibtn6.setImageResource(board.getState(Position.BOTTOM_LEFT).res)
        binding.ibtn7.setImageResource(board.getState(Position.BOTTOM_CENTER).res)
        binding.ibtn8.setImageResource(board.getState(Position.BOTTOM_RIGHT).res)
    }

    private fun updateGameStatus(boardState: BoardState) = when (boardState) {
        BoardState.CIRCLE_WIN -> {binding.lblResult.visibility = View.VISIBLE
            binding.lblResult.text = "Nyertél"}
        BoardState.CROSS_WIN
        -> {
            binding.lblResult.visibility = View.VISIBLE
            binding.lblResult.text = "Veszítettél"
        }
        BoardState.DRAW
        -> {
            binding.lblResult.visibility = View.VISIBLE
            binding.lblResult.text = "Döntetlen"
        }
        BoardState.INCOMPLETE
        -> {
            binding.lblResult.visibility = View.GONE
        }

    }

}