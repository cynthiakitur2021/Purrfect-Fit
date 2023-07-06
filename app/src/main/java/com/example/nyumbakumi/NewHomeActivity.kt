package com.example.nyumbakumi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import android.content.Intent
import android.net.Uri
import android.view.View

class NewHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var cardProgress: CardView
        lateinit var cardWinfo: CardView
        lateinit var cardWPlan: CardView
        lateinit var cardSleep: CardView
        lateinit var cardmarkprogress: CardView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_home)
        cardProgress = findViewById(R.id.mcardprogress)
        cardWinfo = findViewById(R.id.mcardinfo)
        cardWPlan = findViewById(R.id.mcardPlan)
        cardSleep = findViewById(R.id.mcardSleep)
        cardmarkprogress = findViewById(R.id.mcardmarkprogress)


        val playlistButton: View = findViewById(R.id.mcardMusic)

        playlistButton.setOnClickListener {
            val url = "https://open.spotify.com/playlist/37i9dQZF1DXdURFimg6Blm?si=r7NILBx7QW6yyi6vmZixLw" // Replace with the desired URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        cardWinfo.setOnClickListener {
            startActivity(Intent(applicationContext,InfoActivity::class.java))
        }
        cardWPlan.setOnClickListener {
            startActivity(Intent(applicationContext,PlanActivity::class.java))
        }
        cardProgress.setOnClickListener {
            startActivity(Intent(applicationContext,Progress::class.java))
        }
        cardSleep.setOnClickListener {
            startActivity(Intent(applicationContext,SleepActivity::class.java))
        }
        cardmarkprogress.setOnClickListener {
            startActivity(Intent(applicationContext,MarkProgressActivity::class.java))
        }
    }
}