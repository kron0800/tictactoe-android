package com.kron.tictactoe

class TicTacToe(
    val player1_name: String,
    val player2_name: String
) {
    var boardPos = mutableListOf<String>()

    fun clearBoard() {
        boardPos = mutableListOf<String>("","","","","","","","","")
    }
}

