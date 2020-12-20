package com.example.tabatatimer.ui



 fun getTimeFromStr(timeStr: String) : Pair<Int, Int> {
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