package com.app.ibmhackchallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.ibmhackchallenge.R
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WindSpeedAdapter(
    private val powers: ArrayList<Double>,
    private val speeds: ArrayList<Double>) : RecyclerView.Adapter<WindSpeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindSpeedAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_windspeed, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: WindSpeedAdapter.ViewHolder, position: Int) {

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val dateTimeFormat = SimpleDateFormat ("dd-MM-yyyy HH:mm", Locale.ENGLISH)
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        val calenderInstance = Calendar.getInstance()

        val date = calenderInstance.time
        var currentDate = dateFormat.format(date)
        currentDate += if (position < 10)
            " 0$position:00"
        else
            " $position:00"
        val currentTimeWithDate = dateTimeFormat.parse(currentDate)
        val currentTime = timeFormat.format(currentTimeWithDate!!)

        holder.bindItems(powers[position], speeds[position], currentTime)
    }

    override fun getItemCount(): Int {
        return powers.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(power: Double, speed: Double, time: String) {

            val textHour = itemView.findViewById(R.id.hour_text) as TextView
            val textWind = itemView.findViewById(R.id.ws_text) as TextView
            val textPower = itemView.findViewById(R.id.power_text) as TextView

            textHour.text = time
            textWind.text = roundDecimal(speed).toString() + " m/s"
            textPower.text = roundDecimal(power).toString() + " W"
        }

        private fun roundDecimal(number: Double): Double {
            val df = DecimalFormat("#.###")
            df.roundingMode = RoundingMode.CEILING
            return df.format(number).toDouble()
        }
    }
}