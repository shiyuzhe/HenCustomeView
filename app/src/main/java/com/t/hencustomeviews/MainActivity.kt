package com.t.hencustomeviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.t.hencustomeviews.databinding.ActivityMainBinding
import com.t.hencustomeviews.drawText.ActClipAndCamera
import com.t.hencustomeviews.drawText.ActDrawText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            actDrawText.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActDrawText::class.java))
            }
            actClipAndCamera.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActClipAndCamera::class.java))
            }
        }
    }
}