package com.example.tabatatimer.ui.dashboard

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    val FORMAT = "%02d"

    public fun plus10(textViewTime: TextView) {
        var currentTime = textViewTime.text.toString()
        var seconds = getTimeFromStr(currentTime).second
        var minutes = getTimeFromStr(currentTime).first
        if(seconds < 50) {
            seconds += 10
        }
        else if(seconds == 50) {
            seconds = 0
            minutes += 1
        }
        currentTime = String.format(FORMAT, minutes) + ":" + String.format(FORMAT, seconds)
        textViewTime.text = currentTime
    }

    public fun minus10(textViewTime: TextView) {
        var currentTime = textViewTime.text.toString()
        var seconds = getTimeFromStr(currentTime).second
        var minutes = getTimeFromStr(currentTime).first
        if(seconds > 0) {
            seconds -= 10
        }
        else if(seconds == 0) {
            if(minutes != 0) {
                seconds = 50
                minutes -= 1
            }
            else {
                seconds = 0
                minutes = 0
            }
        }
        currentTime = String.format(FORMAT, minutes) + ":" + String.format(FORMAT, seconds)
        textViewTime.text = currentTime
    }

    public fun plusOrMinus1(textViewSet: TextView, add: Boolean = true) {
        var currentSetNumber = textViewSet.text.toString().toInt()
        if(add) {
            currentSetNumber += 1
        }
        else if(currentSetNumber != 0 && !add) {
            currentSetNumber -= 1
        }
        else if(currentSetNumber == 0 && !add) {
            currentSetNumber = 0
        }
        textViewSet.text = currentSetNumber.toString()
    }

    public fun getTimeFromStr(timeStr: String) : Pair<Int, Int> {
        val tmpList = timeStr.split(":")
        val minutes = tmpList[0].toInt()
        val seconds = tmpList[1].toInt()
        return Pair(minutes, seconds)
    }

    fun convertSecondsToMinutes(sec: Int): Pair<Int, Int> {
        val minutes = sec / 60
        val seconds = sec % 60
        return Pair(minutes, seconds)
    }

    fun convertMinutesToSeconds(min: Int, sec: Int): Int {
        return min * 60 + sec
    }
}
