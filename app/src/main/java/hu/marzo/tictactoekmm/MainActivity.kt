package hu.marzo.tictactoekmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import hu.marzo.tictactoekmm.databinding.ActivityMainBinding
import hu.marzo.tictactoekmm.databinding.GameFragmentBinding

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { gameViewModel.resetBoard() }
    }
}