package com.app.ibmhackchallenge.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.app.ibmhackchallenge.R
import com.app.ibmhackchallenge.adapter.CardAdapter
import com.app.ibmhackchallenge.model.Card
import kotlinx.android.synthetic.main.activity_how_it_works.*


class HowItWorksActivity : AppCompatActivity() {

    var viewPager: ViewPager? = null
    var adapter: CardAdapter? = null
    var cards: ArrayList<Card>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_it_works)

        val backButton: Button? = findViewById(R.id.backHIW)

        cards = ArrayList()
        cards!!.add(
            Card(
                R.string.heading3,
                R.string.description3
            )
        )
        cards!!.add(
            Card(
                R.string.heading2,
                R.string.description2
            )
        )
        cards!!.add(
            Card(
                R.string.heading4,
                R.string.description4
            )
        )
        cards!!.add(
            Card(
                R.string.heading5,
                R.string.description5
            )
        )
        adapter = CardAdapter(cards!!, this)
        viewPager = findViewById(R.id.viewPagerHIW)
        viewPager!!.adapter = adapter
        viewPager!!.setPadding(30, 0, 30, 0)
        indicatorHIW.setViewPager(viewPager)

        backButton?.setOnClickListener {
            val intent = Intent(this@HowItWorksActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }

    }
}