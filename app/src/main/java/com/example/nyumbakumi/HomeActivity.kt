package com.example.nyumbakumi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    lateinit var cardAddhouse:CardView
    lateinit var cardViewhouse:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        cardAddhouse = findViewById(R.id.mcardAddhouse)
        cardViewhouse =findViewById(R.id.mcardViewhouse)

        cardAddhouse.setOnClickListener {
         startActivity(Intent(applicationContext,Registration::class.java))
        }
        cardViewhouse.setOnClickListener {
         startActivity(Intent(applicationContext,ViewHousesActivity::class.java))
        }
    }
}