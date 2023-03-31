package com.kron.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kron.tictactoe.databinding.FragmentSingleplayerGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SingleplayerGame.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleplayerGame : Fragment() {
    private var _binding: FragmentSingleplayerGameBinding? = null
    private val binding get() = _binding!!

    private var player1Name: String? = null
    private var player2Name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player1Name = it.getString(PLAYER1_BUNDLE)
            player2Name = it.getString(PLAYER2_BUNDLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleplayerGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBasado.text = "$player1Name vs $player2Name"
    }

    companion object {
        const val PLAYER1_BUNDLE = "PLAYER1_NAME"
        const val PLAYER2_BUNDLE = "PLAYER2_NAME"

        @JvmStatic
        fun newInstance(player1Name: String, player2Name: String) =
            SingleplayerGame().apply {
                arguments = Bundle().apply {
                    putString(PLAYER1_BUNDLE, player1Name)
                    putString(PLAYER2_BUNDLE, player2Name)
                }
            }
    }
}