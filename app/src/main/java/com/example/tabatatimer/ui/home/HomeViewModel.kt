package com.example.tabatatimer.ui.home

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabatatimer.R
import com.example.tabatatimer.ui.convertMinutesToSeconds
import com.example.tabatatimer.ui.convertSecondsToMinutes
import com.example.tabatatimer.ui.getTimeFromStr

class HomeViewModel : ViewModel() {

    val FORMAT = "%02d"

    private var setNumber: Int = 0
    private var workSeconds: Int = 0
    private var restSeconds: Int = 0
    private var currStage: Int = 0
    private var currentSetNumber: Int = 0
    private var currentWorkSeconds: Int = 0
    private var currentTime: Int = 0
    private var currentRestSeconds: Int = 0
    private var isPaused: Boolean = false

    private var timer: CountDownTimer? = null

     fun setTimerConfigs(setNum: Int, workSec:Int,restSec:Int){
        setNumber = setNum
        workSeconds = workSec
        restSeconds = restSec
        currentSetNumber = setNumber
    }


     fun decreaseTV(textViewTime: TextView,mpStart:MediaPlayer) {
        var currentTime = textViewTime.text.toString()
        var seconds = getTimeFromStr(currentTime).second
        var minutes = getTimeFromStr(currentTime).first
         if (minutes != 0) {
             if (seconds == 0) {
                 seconds = 59
                 minutes -= 1
             } else {
                 seconds -= 1
             }
         } else {
             if (seconds in 1..4) {
                 seconds -= 1
                 if(seconds == 0) {
                     mpStart.start()
                     seconds = 0
                     minutes = 0
                 }
                 else {
                     mpStart.start()
                 }
             } else {
                 seconds -= 1
             }
         }
        currentTime = String.format(FORMAT, minutes) + ":" + String.format(FORMAT, seconds)
        textViewTime.text = currentTime
    }


     fun startTimer(sec: Int,timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context,mpStart: MediaPlayer) {
        timer = object : CountDownTimer((sec * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                decreaseTV(timeTV,mpStart)
            }

            override fun onFinish() {
                if (currentSetNumber != 0) {
                    if (currStage == 0 || currStage == 2) {
                        iniWorkout(timeTV,stepCountTV,stageTV,context,mpStart)
                    } else if (currStage == 1) {
                        iniRest(timeTV,stepCountTV,stageTV,context,mpStart)
                    } else {
                        //finish activity
                    }
                } else {
                    iniDone(timeTV,stepCountTV,stageTV,context)
                }
            }
        }
        (timer as CountDownTimer).start()
    }

     fun cancelTimer() {
        timer?.cancel()
    }

     fun iniGetReady(playPauseB:ImageView,timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context,mpStart: MediaPlayer) {
        currStage = 0
        stageTV.isVisible = true
        stepCountTV.isVisible = true
        playPauseB.setImageResource(R.drawable.ic_pause_24px)
        stepCountTV.text = context.getString(R.string.upper_set) + " " + currentSetNumber.toString()
        stageTV.text = context.getString(R.string.upper_get_ready)
        timeTV.text = context.getString(R.string.ini_time)
        startTimer(5,timeTV,stepCountTV,stageTV,context,mpStart)
    }

     fun iniWorkout(timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context,mpStart: MediaPlayer) {
        currStage = 1
        stepCountTV.text = context.getString(R.string.upper_set) + " " + currentSetNumber.toString()
        stageTV.text = context.getString(R.string.upper_work_it)
        timeTV.text = "${String.format(
            FORMAT,
            convertSecondsToMinutes(workSeconds).first
        )}:${String.format(FORMAT, convertSecondsToMinutes(workSeconds).second + 1)}"
        startTimer(workSeconds + 1,timeTV,stepCountTV,stageTV,context,mpStart)
    }

     fun iniRest(timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context,mpStart: MediaPlayer) {
        currStage = 2
        stageTV.text = context.getString(R.string.upper_rest_now)
        timeTV.text = "${String.format(
            FORMAT,
            convertSecondsToMinutes(restSeconds).first
        )}:${String.format(FORMAT, convertSecondsToMinutes(restSeconds).second + 1)}"
        startTimer(restSeconds + 1,timeTV,stepCountTV,stageTV,context,mpStart)
        currentSetNumber -= 1
    }

     fun iniDone(timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context) {
        currStage = -1
        stageTV.isVisible = false
        stepCountTV.isVisible = false
        timeTV.text = context.getString(R.string.upper_done)
        //playPauseB.setImageResource(R.drawable.ic_play_24px)
    }

    fun replayValues() {

    }

    fun pausePressed(playPauseB:ImageView,timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context,mpStart: MediaPlayer){
        if(!isPaused) {
            cancelTimer()
            playPauseB.setImageResource(R.drawable.ic_play_24px)
            isPaused = true
        }
        else {
            if(currStage != -1) {
                currentTime = convertMinutesToSeconds(
                    getTimeFromStr(timeTV.text.toString()).first,
                    getTimeFromStr(timeTV.text.toString()).second
                )
                playPauseB.setImageResource(R.drawable.ic_pause_24px)
                startTimer(currentTime,timeTV,stepCountTV,stageTV,context,mpStart)
                isPaused = false
            }
        }
    }

    fun replayPressed(playPauseB:ImageView,timeTV: TextView, stepCountTV: TextView, stageTV: TextView, context: Context,mpStart: MediaPlayer) {
        cancelTimer()
        currentSetNumber = setNumber
        iniGetReady(playPauseB,timeTV,stepCountTV,stageTV,context,mpStart)
    }

}