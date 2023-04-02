package com.kron.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kron.tictactoe.databinding.FragmentSingleplayerRegisterBinding

class SingleplayerRegister : Fragment() {
    private var _binding: FragmentSingleplayerRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleplayerRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnStartGame.setOnClickListener { checkValues() }
    }

    private fun checkValues() {
        if (binding.etPlayer1Name.text.isNotEmpty() && binding.etPlayer2Name.text.isNotEmpty()) {
            startGame()
        } else {
            Toast.makeText(activity?.applicationContext, "You must fill both names in order to start the game", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startGame() {
        val gameFragment = SingleplayerGame.newInstance(binding.etPlayer1Name.text.toString(), binding.etPlayer2Name.text.toString(), binding.cbClearBoardAuto.isChecked)
        val transaction = activity?.supportFragmentManager?.beginTransaction()

        transaction?.replace(R.id.fragmentContainer, gameFragment)
        transaction?.setReorderingAllowed(true)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

}