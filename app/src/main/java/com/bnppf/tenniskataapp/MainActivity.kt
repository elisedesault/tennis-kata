package com.bnppf.tenniskataapp

import android.os.Bundle
import android.support.constraint.Group
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var newGameButton: Button

    private lateinit var buttonsGroup: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newGameButton = findViewById(R.id.btn_new_game)
        buttonsGroup = findViewById(R.id.group_buttons)
        val score = findViewById<TextView>(R.id.tv_score)
        val playerOneButton = findViewById<Button>(R.id.btn_player_one)
        val playerTwoButton = findViewById<Button>(R.id.btn_player_two)

        val playerOne = Player("Serena")
        val playerTwo = Player("Venus")
        val game = Game(playerOne, playerTwo)

        playerOneButton.apply {
            text = "Serena"
            play(game, playerOne, score)
        }
        playerTwoButton.apply {
            text = "Venus"
            play(game, playerTwo, score)
        }

        startNewGame(game, score)
    }

    private fun startNewGame(game: Game, score: TextView) {
        newGameButton.setOnClickListener {
            game.resetPlayersScore()
            buttonsGroup.visibility = View.VISIBLE
            newGameButton.visibility = View.GONE
            score.text = ""
        }
    }

    private fun Button.play(game: Game, player: Player, score: TextView) {
        setOnClickListener {
            kotlin.run {
                game.increaseScore(player)
                if (game.checkGameStatus() == GameStatus.WIN) {
                    buttonsGroup.visibility = View.GONE
                    newGameButton.visibility = View.VISIBLE
                }
                score.text = game.getScore()
            }
        }
    }
}


