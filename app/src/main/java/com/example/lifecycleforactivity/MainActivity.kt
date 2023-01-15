package com.example.lifecycleforactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val TAG = "FirstActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: Called")

        val goToSecond = findViewById<Button>(R.id.goToSecondActivity)

        goToSecond.setOnClickListener{
            Log.i(TAG, "Going to second activity")
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: Called")
    }
}