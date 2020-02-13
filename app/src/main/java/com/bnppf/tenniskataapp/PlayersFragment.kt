package com.bnppf.tenniskataapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_players.view.*

class PlayersFragment : Fragment() {
    private lateinit var edPlayerOne: EditText
    private lateinit var edPlayerTwo: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_players, container, false)
        edPlayerOne = view.ed_player_one
        edPlayerTwo = view.ed_player_two

        view.btn_save_players.setOnClickListener{
            val action = PlayersFragmentDirections.actionPlayersFragmentToGameFragment(edPlayerOne.text.toString(), edPlayerTwo.text.toString())
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }
}
