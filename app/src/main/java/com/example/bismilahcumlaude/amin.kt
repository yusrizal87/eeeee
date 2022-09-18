package com.example.bismilahcumlaude

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


@Suppress("DEPRECATION")
class amin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashsc)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, home::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}