package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var time: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        time = SystemClock.elapsedRealtime() - chronometer.base
        outState.putLong(TIME, time)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        time = savedInstanceState.getLong(TIME)
    }

    private fun initViews() {
        fabStart.setOnClickListener { start() }
        fabPause.setOnClickListener { pause() }
        fabStop.setOnClickListener { stop() }
    }

    private fun start() {
        chronometer.base = SystemClock.elapsedRealtime() - time
        chronometer.start()
    }

    private fun pause() {
        time = SystemClock.elapsedRealtime() - chronometer.base
        chronometer.stop()
    }

    private fun stop() {
        time = 0
        chronometer.base = SystemClock.elapsedRealtime() - time
        chronometer.stop()
    }

    companion object {
        const val TIME = "time"
    }
}