package com.bnppf.tenniskataapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_game.view.*


class GameFragment : Fragment() {

    val args: GameFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)
        val score = view.tv_score
        val btnPlayerOne = view.btn_player_one
        val playerOneName = args.playerOne
        val btnPlayerTwo = view.btn_player_two
        val playerTwoName = args.playerTwo

        val playerOne = Player(playerOneName)
        val playerTwo = Player(playerTwoName)
        val game = Game(playerOne, playerTwo)

        btnPlayerOne.apply {
            text = playerOneName
            play(game, playerOne, score)
        }
        btnPlayerTwo.apply {
            text = playerTwoName
            play(game, playerTwo, score)
        }
        return view
    }

    private fun Button.play(game: Game, player: Player, score: TextView) {
        setOnClickListener {
            kotlin.run {
                game.increaseScore(player)
                if (game.checkGameStatus() == GameStatus.WIN) {

                    val action = GameFragmentDirections.actionGameFragmentToScoreFragment(game.getScore())
                    Navigation.findNavController(it).navigate(action)
                }

                score.text = game.getScore()
            }
        }
    }
}
