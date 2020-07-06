package com.app.ibmhackchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.app.ibmhackchallenge.R
import com.app.ibmhackchallenge.model.Card


class CardAdapter(private val cards: ArrayList<Card>, context: Context) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private var context: Context

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return cards.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater!!.inflate(R.layout.item_card, container, false)
        val heading: TextView
        val description: TextView
        heading = view.findViewById(R.id.title)
        description = view.findViewById(R.id.desc)
        heading.setText(cards[position].heading)
        description.setText(cards[position].description)
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

    init {
        this.context = context
    }

}