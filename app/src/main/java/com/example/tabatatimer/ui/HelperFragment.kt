package com.example.tabatatimer.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.tabatatimer.R
import com.example.tabatatimer.SettingsActivity
import com.example.tabatatimer.TimerActivity
import com.example.tabatatimer.ui.home.HomeViewModel

class HelperFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(activity, SettingsActivity::class.java)
        this.startActivity(intent)
    }


}