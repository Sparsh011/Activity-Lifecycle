package com.example.lifecycleforactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val TAG = "FirstActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: Called")


//        Q. Why shouldn't we write logical part related to views in onStart?
//        Ans. All the logic part should be written in onCreate and not in onStart because, let's say we want to show a toast or do something whenever the activity is launched for the first time and if we write that code in onStart, then if we navigate to any other activity and come back to the original activity, then that toast will be shown again and we don't want this because activity IS NOT recreated at that moment. Also, all the processing will be done again and again in this scenario

//        Follow up ques. But if the device is rotated, then onCreate will be called again, then the toast will be shown again, how to overcome this problem?
//        Ans. By storing whether the toast was shown or not in bundle or a global variable. Bundle's data lives as long as the application lives.

        Toast.makeText(this, "Hello From onCreate!", Toast.LENGTH_SHORT).show()

        val goToSecond = findViewById<Button>(R.id.goToSecondActivity)

        goToSecond.setOnClickListener{
            Log.i(TAG, "Going to second activity")
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: called")
        Toast.makeText(this, "Hello From onStart!", Toast.LENGTH_SHORT).show()
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