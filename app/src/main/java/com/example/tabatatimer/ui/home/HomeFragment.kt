package com.example.tabatatimer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tabatatimer.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_play_pause.setOnClickListener{v ->
            if(homeViewModel.timerState.value == HomeViewModel.TimerState.Paused){
                homeViewModel.timerState.value = HomeViewModel.TimerState.Running
            }
            else if (homeViewModel.timerState.value == HomeViewModel.TimerState.Running){
                homeViewModel.timerState.value = HomeViewModel.TimerState.Paused
            }
        }

        button_replay.setOnClickListener{v ->

        }

        button_stop.setOnClickListener{v ->

        }
    }
}