package com.bnppf.tenniskataapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_game.view.tv_score
import kotlinx.android.synthetic.main.fragment_score.view.*


class ScoreFragment : Fragment() {
    val args: ScoreFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_score, container, false)
        view.tv_score.text = args.score

        view.btn_new_game.setOnClickListener{
            val action = ScoreFragmentDirections.actionScoreFragmentPop2()
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }
}
