package com.example.nyumbakumi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SleepActivity : AppCompatActivity() {
    lateinit var sleepTimeEditText: EditText
    lateinit var wakeTimeEditText: EditText
    lateinit var calculateButton: Button
    lateinit var hoursSleptTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)
        sleepTimeEditText = findViewById(R.id.sleepTimeEditText)
        wakeTimeEditText = findViewById(R.id.wakeTimeEditText)
        calculateButton = findViewById(R.id.calculateButton)
        hoursSleptTextView = findViewById(R.id.hoursSleptTextView)

        calculateButton.setOnClickListener {
            val sleepTime: String = sleepTimeEditText.text.toString()
            val wakeTime: String = wakeTimeEditText.text.toString()

            // Assuming sleepTime and wakeTime are in HH:mm format
            val sleepHour: Int = sleepTime.substringBefore(":").toInt()
            val sleepMinute: Int = sleepTime.substringAfter(":").toInt()
            val wakeHour: Int = wakeTime.substringBefore(":").toInt()
            val wakeMinute: Int = wakeTime.substringAfter(":").toInt()

            // Calculate hours slept
            val totalMinutes: Int = (wakeHour * 60 + wakeMinute) - (sleepHour * 60 + sleepMinute)
            val hoursSlept: Int = totalMinutes / 60
            val minutesSlept: Int = totalMinutes % 60

            // Display the result
            hoursSleptTextView.text = "You slept for $hoursSlept hours and $minutesSlept minutes."
        }


    }
}