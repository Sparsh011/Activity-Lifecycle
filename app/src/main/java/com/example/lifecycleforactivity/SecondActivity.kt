package com.example.lifecycleforactivity

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit


class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i(TAG, "onCreate: Called")


//        Result of launching a coroutine and making dialog cover the entire screen: No lifecycle method was called
        val showDialogButton = findViewById<Button>(R.id.showDialog)
        showDialogButton.setOnClickListener {
//            showDialog()
            showAnotherDialog()
        }

        val showToast = findViewById<Button>(R.id.showToastBtn)
        showToast.setOnClickListener{
            Toast.makeText(applicationContext, "Hello Toast", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showAnotherDialog(){
//        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
//        builder.setTitle("Dialog Title")
//            .setMessage("This is a sample dialog")
//            .setPositiveButton("OK", object : DialogInterface.OnClickListener{
//                override fun onClick(p0: DialogInterface?, p1: Int) {
//                    Toast.makeText(applicationContext, "Hello", Toast.LENGTH_SHORT).show()
//                    p0?.dismiss()
//                }
//            })
//
//        val dialog = builder.create()
//        dialog.show();

        Log.i(TAG, "showAnotherDialog: ${lifecycle.currentState}")

        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_progress, null)

        val dialog = Dialog(this)
        dialog.setContentView(dialogView)
        dialog.show()

        dialogView.findViewById<Button>(R.id.btnSendRequest).setOnClickListener{
            dialog.dismiss()
        }

        if (this@SecondActivity.isFinishing || this@SecondActivity.isDestroyed){
            Toast.makeText(applicationContext, "Destroyed/Finished", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showDialog() {
//        mProgressDialog = Dialog(this)
//
//        mProgressDialog?.let {
//            it.setContentView(R.layout.dialog_custom_progress)
//            it.show()
//        }  This normal stuff doesn't call any of the lifecycle methods.
        val builder = Dialog(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_custom_progress, null)
        val btnLaunchCoroutine = dialogLayout.findViewById<Button>(R.id.btnSendRequest)


//        Below code is written so that dialog covers the whole screen
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(builder.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT

        builder.setContentView(dialogLayout)
        builder.show()
        builder.window?.attributes = lp

        btnLaunchCoroutine.setOnClickListener {
            launchCoroutine()
        }
    }

    private fun launchCoroutine() {
        var a = 1;
        CoroutineScope(Dispatchers.IO).launch {

            val start = System.currentTimeMillis()
            val hmsStart = String.format(
                "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(start),
                TimeUnit.MILLISECONDS.toMinutes(start) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(start)),
                TimeUnit.MILLISECONDS.toSeconds(start) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(start))
            )
            while (a < 1000000000) {
                a++
            }
            val end = System.currentTimeMillis()
            val hmsEnd = String.format(
                "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(end),
                TimeUnit.MILLISECONDS.toMinutes(end) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(end)),
                TimeUnit.MILLISECONDS.toSeconds(end) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(end))
            )

            Log.i(TAG, "Inside coroutine : \nStarted at : $hmsStart \nEnded at : $hmsEnd")

            if (a == 1000000000) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Now exiting coroutine...", Toast.LENGTH_SHORT).show()
                    return@withContext
                }
                return@launch
            }
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