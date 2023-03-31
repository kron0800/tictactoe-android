 package com.kron.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kron.tictactoe.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
//        window.statusBarColor =
        window.navigationBarColor

    }

     private fun initListeners() {
         binding.btnSingleplayer.setOnClickListener { playSingleplayer() }
         binding.btnMultiplayer.setOnClickListener { playMultiplayer() }
     }

     private fun playMultiplayer() {
         Toast.makeText(this, "Multiplayer mode isn't developed... yet!", Toast.LENGTH_SHORT).show()
     }

     private fun playSingleplayer() {
         val i = Intent(this, Singleplayer::class.java)
         startActivity(i)
     }
 }