package com.kron.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.kron.tictactoe.databinding.ActivitySingleplayerBinding

class Singleplayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivitySingleplayerBinding

        super.onCreate(savedInstanceState)
        binding = ActivitySingleplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SingleplayerRegister>(R.id.fragmentContainer)
            }
        }
    }
}