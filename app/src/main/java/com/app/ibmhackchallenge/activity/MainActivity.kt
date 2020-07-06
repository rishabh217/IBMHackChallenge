package com.app.ibmhackchallenge.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.app.ibmhackchallenge.R
import com.app.ibmhackchallenge.adapter.CardAdapter
import com.app.ibmhackchallenge.model.Card
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var analysisButton: Button
    private lateinit var calculateButton: Button
    private lateinit var howItWorksButton: TextView
    private lateinit var githubButton: ImageButton

    var viewPager: ViewPager? = null
    var adapter: CardAdapter? = null
    var cards: ArrayList<Card>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analysisButton = findViewById(R.id.analysis_button)
        calculateButton = findViewById(R.id.calculate)
        githubButton = findViewById(R.id.github_repo)
        howItWorksButton = findViewById(R.id.how_it_works_button)

        cards = ArrayList()
        cards!!.add(
            Card(
                R.string.heading1,
                R.string.description1
            )
        )
        cards!!.add(
            Card(
                R.string.heading6,
                R.string.description6
            )
        )
        cards!!.add(
            Card(
                R.string.heading7,
                R.string.description7
            )
        )
        cards!!.add(
            Card(
                R.string.heading8,
                R.string.description8
            )
        )
        adapter = CardAdapter(cards!!, this)
        viewPager = findViewById(R.id.viewPagerMain)
        viewPager!!.adapter = adapter
        viewPager!!.setPadding(30, 0, 30, 0)

        calculateButton.setOnClickListener {
            val firstParam = param1.text.toString()
            val secondParam = param2.text.toString()
            val thirdParam = param3.text.toString()
            if (firstParam.isEmpty() || secondParam.isEmpty() || thirdParam.isEmpty()) {
                Toast.makeText(this, "All Parameters are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val a = firstParam.toFloat()
            val b = secondParam.toFloat()
            val c = thirdParam.toFloat()
            result.text = (a + b + c).toString()
        }

        githubButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/rishabh217/SBSPS-Challenge-4112-Predicting-the-energy-output-of-wind-turbine-based-on-weather-condition")
            startActivity(intent)
        }

        analysisButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AnalysisActivity::class.java))
        }

        howItWorksButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, HowItWorksActivity::class.java))
        }
    }
}