package com.kron.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.kron.tictactoe.databinding.FragmentSingleplayerGameBinding
import java.util.*


class SingleplayerGame : Fragment() {
    private var _binding: FragmentSingleplayerGameBinding? = null
    private val binding get() = _binding!!

    // Variables
    private lateinit var game: TicTacToe
    private var player1Name: String? = null
    private var player2Name: String? = null
    private var clearBoardAutomatically: Boolean = true
    private var player1Score: Int = 0
    private var player2Score: Int = 0
    private var playerTurn = player1Name
    private var playerTurnMovement = R.drawable.ic_xcross


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player1Name = it.getString(PLAYER1_BUNDLE)
            player2Name = it.getString(PLAYER2_BUNDLE)
            clearBoardAutomatically = it.getBoolean(CLEARBOARDAUTOMATICALLY_BUNDLE)
        }
        player1Name =
            player1Name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        player2Name =
            player2Name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        playerTurn = player1Name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleplayerGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val PLAYER1_BUNDLE = "PLAYER1_NAME"
        const val PLAYER2_BUNDLE = "PLAYER2_NAME"
        const val CLEARBOARDAUTOMATICALLY_BUNDLE = "CLEARBOARDAUTOMATICALLY_BUNDLE"

        @JvmStatic
        fun newInstance(player1Name: String, player2Name: String, clearBoardAutomatically: Boolean = true) =
            SingleplayerGame().apply {
                arguments = Bundle().apply {
                    putString(PLAYER1_BUNDLE, player1Name)
                    putString(PLAYER2_BUNDLE, player2Name)
                    putBoolean(CLEARBOARDAUTOMATICALLY_BUNDLE, clearBoardAutomatically)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game = initGame() // Starts a new instance of the TicTacToe class
        initUI()
        initListeners()
    }

    private fun initGame(): TicTacToe {
        val game = TicTacToe(
            player1_name = player1Name ?: "Player 1",
            player2_name = player2Name ?: "Player 2"
        )
        game.clearBoard()
        return game
    }

    private fun initUI() {
        binding.tvPlayer1Score.text = getString(R.string.player1_score, player1Name, player1Score)
        binding.tvPlayer2Score.text = getString(R.string.player2_score, player2Name, player2Score)
        binding.tvPlayerTurn.text = getString(R.string.its_player_turn, playerTurn ?: "Player 1")
    }

    private fun initListeners() {
        // Check one previous position as this is a list and therefore must use index

        binding.ivBoardPos1.setOnClickListener {
            if (isPositionAvailable(0) != "unavailable") {
                binding.ivBoardPos1.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos2.setOnClickListener {
            if (isPositionAvailable(1) != "unavailable") {
                binding.ivBoardPos2.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos3.setOnClickListener {
            if (isPositionAvailable(2) != "unavailable") {
                binding.ivBoardPos3.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos4.setOnClickListener {
            if (isPositionAvailable(3) != "unavailable") {
                binding.ivBoardPos4.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos5.setOnClickListener {
            if (isPositionAvailable(4) != "unavailable") {
                binding.ivBoardPos5.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos6.setOnClickListener {
            if (isPositionAvailable(5) != "unavailable") {
                binding.ivBoardPos6.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos7.setOnClickListener {
            if (isPositionAvailable(6) != "unavailable") {
                binding.ivBoardPos7.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos8.setOnClickListener {
            if (isPositionAvailable(7) != "unavailable") {
                binding.ivBoardPos8.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.ivBoardPos9.setOnClickListener {
            if (isPositionAvailable(8) != "unavailable") {
                binding.ivBoardPos9.setImageResource(playerTurnMovement)
                checkGameStatus()
            }
        }
        binding.btnResetGame.setOnClickListener { restartGame() }

    }

    private fun checkGameStatus() {
        // clear board automatically if it's full (and enabled)
        if (!game.boardPos.contains("") && clearBoardAutomatically) {
            restartGame()
        } else {
            winCheck()
            switchTurn()
        }
    }

    private fun restartGame() {
        game.clearBoard()
        for (boardPos in listOf<ImageView>(
            binding.ivBoardPos1,
            binding.ivBoardPos2,
            binding.ivBoardPos3,
            binding.ivBoardPos4,
            binding.ivBoardPos5,
            binding.ivBoardPos6,
            binding.ivBoardPos7,
            binding.ivBoardPos8,
            binding.ivBoardPos9
        )) {
            boardPos.setImageResource(R.drawable.empty)
        }
    }

    private fun winCheck() {
        // We check every position to see if there is a completed line
        if ((game.boardPos[0] == game.boardPos[1] && game.boardPos[1] != "" && game.boardPos[2] == game.boardPos[1]) ||      // First line Horizontal check
                (game.boardPos[3] == game.boardPos[4] && game.boardPos[4] != "" && game.boardPos[5] == game.boardPos[4]) ||  // Second line Horizontal check
                (game.boardPos[6] == game.boardPos[7] && game.boardPos[7] != "" && game.boardPos[8] == game.boardPos[7]) ||  // Third line Horizontal check
                (game.boardPos[0] == game.boardPos[3] && game.boardPos[3] != "" && game.boardPos[6] == game.boardPos[3]) ||  // First line Vertical check
                (game.boardPos[1] == game.boardPos[4] && game.boardPos[4] != "" && game.boardPos[7] == game.boardPos[4]) ||  // Second line Vertical check
                (game.boardPos[2] == game.boardPos[5] && game.boardPos[5] != "" && game.boardPos[8] == game.boardPos[5]) ||  // Third line Vertical check
                (game.boardPos[2] == game.boardPos[4] && game.boardPos[4] != "" && game.boardPos[6] == game.boardPos[4]) ||  // Left to right diagonal check
                (game.boardPos[0] == game.boardPos[4] && game.boardPos[4] != "" && game.boardPos[8] == game.boardPos[4]))    // Right to left diagonal check
        {
            if (playerTurn == player1Name) { // If there's a completed line, the last player who did a movement scores a point
                Toast.makeText(activity, "$player1Name scored!", Toast.LENGTH_SHORT).show()
                player1Score += 1
            } else {
                player2Score += 1
                Toast.makeText(activity, "$player2Name scored!", Toast.LENGTH_SHORT).show()}
            restartGame()
            switchTurn()
        }
    }


    private fun isPositionAvailable(selectedPosition: Int): Any {
        if (game.boardPos[selectedPosition] != "") {
            return "unavailable"
        } else {
            val movement = if (playerTurn == player1Name) {
                "X"
            } else {
                "O"
            }
            game.boardPos[selectedPosition] = movement
            Log.i("BOARD_PROGRESS", game.boardPos.toString())
            return movement
        }
    }

    private fun switchTurn() {
        if (playerTurn == player1Name) {
            playerTurn = player2Name
            playerTurnMovement = R.drawable.ic_circle
        } else {
            playerTurn = player1Name
            playerTurnMovement = R.drawable.ic_xcross
        }
        initUI()
    }

}