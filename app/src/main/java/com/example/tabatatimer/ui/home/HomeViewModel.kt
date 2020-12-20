package com.example.tabatatimer.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    enum class TimerState{
        Stopped,Paused, Running
    }
    private lateinit var timer: CountDownTimer
    val timerState = MutableLiveData<TimerState>()
    private var timerLengthSeconds = 0L
    private var secondsRemaining = 0L

    init {
        timerState.value = TimerState.Stopped
    }
}