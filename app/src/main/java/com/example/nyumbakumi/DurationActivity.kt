package com.example.nyumbakumi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class DurationActivity : AppCompatActivity() {
     lateinit var btnsubmit : Button
     lateinit var edthours : EditText
     lateinit var edtminutes : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duration)

        btnsubmit = findViewById(R.id.mbtnLogin)
        edthours = findViewById(R.id.edthours)
        edtminutes = findViewById(R.id.edtminutes)

        btnsubmit.setOnClickListener{
            startActivity(Intent(this,MarkProgressActivity::class.java))

        }
    }
}