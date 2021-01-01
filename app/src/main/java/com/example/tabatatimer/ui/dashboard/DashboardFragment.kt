package com.example.tabatatimer.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.tabatatimer.R
import com.example.tabatatimer.TimerActivity
import com.example.tabatatimer.ui.convertMinutesToSeconds
import com.example.tabatatimer.ui.getTimeFromStr
import com.example.tabatatimer.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var setNumberTV: TextView
    private lateinit var setNumberMinusB: ImageView
    private lateinit var setNumberPlusB: ImageView

    private lateinit var workIntervalTV: TextView
    private lateinit var workIntervalMinusB: ImageView
    private lateinit var workIntervalPlusB: ImageView

    private lateinit var restIntervalTV: TextView
    private lateinit var restIntervalMinusB: ImageView
    private lateinit var restIntervalPlusB: ImageView
    private lateinit var startB: Button

    private val INTENT_SET_NUMBER = "sets_number"
    private val INTENT_WORK_INTERVAL = "work_interval"
    private val INTENT_REST_INTERVAL = "rest_interval"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNumberTV = a_main_text_view_sets_number
        setNumberMinusB = a_main_image_button_sets_minus
        setNumberPlusB = a_main_image_button_sets_plus
        workIntervalTV = a_main_text_view_work_interval_time
        workIntervalMinusB = a_main_image_button_work_interval_minus
        workIntervalPlusB = a_main_image_button_work_interval_plus
        restIntervalTV = a_main_text_view_rest_interval_time
        restIntervalMinusB = a_main_image_button_rest_interval_minus
        restIntervalPlusB = a_main_image_button_rest_interval_plus
        startB = a_main_button_start


        setNumberMinusB.setOnClickListener {
            dashboardViewModel.plusOrMinus1(setNumberTV, false)
        }
        setNumberPlusB.setOnClickListener {
            dashboardViewModel.plusOrMinus1(setNumberTV)
        }
        workIntervalPlusB.setOnClickListener {
            dashboardViewModel.plus10(workIntervalTV)
        }
        workIntervalMinusB.setOnClickListener {
            dashboardViewModel.minus10(workIntervalTV)
        }
        restIntervalPlusB.setOnClickListener {
            dashboardViewModel.plus10(restIntervalTV)
        }
        restIntervalMinusB.setOnClickListener {
            dashboardViewModel.minus10(restIntervalTV)
        }

        startB.setOnClickListener {
            val setNumber: Int = setNumberTV.text.toString().toInt()
            val workSeconds: Int = convertMinutesToSeconds(
               getTimeFromStr(workIntervalTV.text.toString()).first,
                getTimeFromStr(workIntervalTV.text.toString()).second
            )
            val restSeconds: Int = convertMinutesToSeconds(
                getTimeFromStr(restIntervalTV.text.toString()).first,
                getTimeFromStr(restIntervalTV.text.toString()).second
            )


            val intent = Intent(activity, TimerActivity::class.java)
            intent.putExtra(INTENT_SET_NUMBER, setNumber)
            intent.putExtra(INTENT_WORK_INTERVAL, workSeconds)
            intent.putExtra(INTENT_REST_INTERVAL, restSeconds)
            this.startActivity(intent)
         }

    }
}