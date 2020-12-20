package com.example.tabatatimer.ui.home

import android.content.Context
import android.os.CountDownTimer
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabatatimer.R
import com.example.tabatatimer.ui.getTimeFromStr

class HomeViewModel : ViewModel() {

    val FORMAT = "%02d"

    private var setNumber: Int = 0
    private var workSeconds: Int = 0
    private var restSeconds: Int = 0
    private var timer: CountDownTimer? = null

    public fun setTimerConfigs(setNum: Int, workSec:Int,restSec:Int){
        setNumber = setNum
        workSeconds = workSec
        restSeconds =restSec
    }


    public fun decreaseTV(textViewTime: TextView) {
        var currentTime = textViewTime.text.toString()
        var seconds = getTimeFromStr(currentTime).second
        var minutes = getTimeFromStr(currentTime).first
        if(seconds == 0 && minutes != 0) {
            seconds = 59
            minutes -= 1
        }
        else if(seconds == 0 && minutes == 0) {
            seconds = 0
            minutes = 0
        }
        else {
            seconds -= 1
        }
        currentTime = String.format(FORMAT, minutes) + ":" + String.format(FORMAT, seconds)
        textViewTime.text = currentTime
    }


    public fun startTimer(sec: Int,timeTV: TextView) {
        timer = object : CountDownTimer((sec * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                decreaseTV(timeTV)
            }
            override fun onFinish() {}
        }
        (timer as CountDownTimer).start()
    }

    public fun cancelTimer() {
        timer?.cancel()
    }

    public fun iniWorkout(timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context) {
        stepCountTV.text = context.getString(R.string.upper_set) + " " + setNumber.toString()
        stageTV.text = context.getString(R.string.upper_get_ready)
        timeTV.text = context.getString(R.string.ini_time)
        startTimer(6,timeTV)
    }

}