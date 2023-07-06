package com.example.nyumbakumi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.cardview.widget.CardView

class Progress : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Perform actions based on the selected radio button
            when (checkedId) {
                R.id.Radio1 -> {
// Radio button 1 is selected
                    val intent = Intent(this, DurationActivity::class.java)
                    startActivity(intent)
                }
                R.id.Radio2 -> {
                    // Radio button 2 is selected
                    val intent = Intent(this, DurationActivity::class.java)
                    startActivity(intent)
                }
                R.id.Radio3 -> {
                    // Radio button 2 is selected
                    val intent = Intent(this, DurationActivity::class.java)
                    startActivity(intent)
                }
                R.id.Radio4 -> {
                    // Radio button 2 is selected
                    val intent = Intent(this, DurationActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }
}