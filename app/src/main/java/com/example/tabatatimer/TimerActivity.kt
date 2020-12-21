package com.example.tabatatimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.tabatatimer.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class TimerActivity: AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var timeTV: TextView
    private lateinit var stageTV: TextView
    private lateinit var stepCountTV: TextView

    private lateinit var playPauseB: ImageView
    private lateinit var replayB: ImageView
    private lateinit var stopB: ImageView

    private var setNumberIni: Int = 0
    private var workSecondsIni: Int = 0
    private var restSecondsIni: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        timeTV = timer_time
        stageTV = timer_stage
        stepCountTV = timer_set_count

        playPauseB = button_play_pause
        stopB = button_stop
        replayB = button_replay

        stopB.setOnClickListener {
            homeViewModel.cancelTimer()
            this.finish()
        }

        replayB.setOnClickListener {
            homeViewModel.replayPressed(playPauseB,timeTV,stepCountTV,stageTV,this)
        }

        playPauseB.setOnClickListener {
            homeViewModel.pausePressed(playPauseB,timeTV,stepCountTV,stageTV,this)
        }

        getValues()
        homeViewModel.setTimerConfigs(setNumberIni,workSecondsIni,restSecondsIni)
        homeViewModel.iniGetReady(playPauseB,timeTV,stepCountTV,stageTV,this)

    }

    private fun getValues() {
        setNumberIni = intent.getIntExtra("sets_number", 0)
        workSecondsIni = intent.getIntExtra("work_interval", 0)
        restSecondsIni = intent.getIntExtra("rest_interval", 0)
    }


}


