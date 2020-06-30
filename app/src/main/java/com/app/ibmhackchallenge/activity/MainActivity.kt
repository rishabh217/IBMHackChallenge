package com.app.ibmhackchallenge.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.ibmhackchallenge.R

class MainActivity : AppCompatActivity() {

    private lateinit var analysisButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analysisButton = findViewById(R.id.analysis_button)

        analysisButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AnalysisActivity::class.java))
        }
    }
}