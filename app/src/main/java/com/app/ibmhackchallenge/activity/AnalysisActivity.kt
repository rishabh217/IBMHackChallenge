package com.app.ibmhackchallenge.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ibmhackchallenge.R
import com.app.ibmhackchallenge.adapter.WindSpeedAdapter
import com.app.ibmhackchallenge.api.GetPointsService
import com.app.ibmhackchallenge.api.RetrofitClientInstance
import com.app.ibmhackchallenge.dto.PointsList
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_analysis.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AnalysisActivity : AppCompatActivity() {

    private lateinit var graph: LineChart
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    private val dateTimeFormat = SimpleDateFormat ("dd-MM-yyyy HH:mm", Locale.ENGLISH)
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    private val calenderInstance = Calendar.getInstance()
    private lateinit var recyclerWindSpeed: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)

        graph = findViewById<LineChart>(R.id.graph)
        graph.description.text = "Prediction of Output Power"
        graph.setNoDataText("Select any day by tapping the date icon!")
        graph.setNoDataTextTypeface(Typeface.MONOSPACE)

        val backButton: Button? = findViewById(R.id.backMain)

        val yAxis: YAxis = graph.axisLeft

        val ll = LimitLine(140f, "Time (Hour)")
        ll.textColor = Color.RED
        ll.labelPosition = LimitLine.LimitLabelPosition.LEFT_BOTTOM
        ll.lineColor = Color.DKGRAY
        ll.typeface = Typeface.MONOSPACE
        yAxis.addLimitLine(ll)

        val fetchDay1 = findViewById<ImageView>(R.id.fetch1)
        val fetchDay2 = findViewById<ImageView>(R.id.fetch2)
        val fetchDay3 = findViewById<ImageView>(R.id.fetch3)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        recyclerWindSpeed = findViewById<RecyclerView>(R.id.rv_windspeed)
        recyclerWindSpeed.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerWindSpeed.isNestedScrollingEnabled = false

        val dayOne = dateFormat.format(calenderInstance.time)
        val calInst1 = Calendar.getInstance()
        val calInst2 = Calendar.getInstance()
        calInst1.add(Calendar.DAY_OF_YEAR, 1)
        calInst2.add(Calendar.DAY_OF_YEAR, 2)
        val dayTwo = dateFormat.format(calInst1.time)
        val dayThree = dateFormat.format(calInst2.time)

        day1.text = dayOne
        day2.text = dayTwo
        day3.text = dayThree

        fetchDay1.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            val service =
                RetrofitClientInstance.retrofitInstance?.create(GetPointsService::class.java)
            val call = service?.getAllPoints()

            call?.enqueue(object : Callback<PointsList> {
                override fun onFailure(call: Call<PointsList>, t: Throwable) {
                    Toast.makeText(this@AnalysisActivity, "Error Reading JSON", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<PointsList>?, response: Response<PointsList>?) {

                    val body = response?.body()
                    val points = body?.points

                    if (points != null) {

                        val power = points.power
                        val speed = points.speed

                        val power1 = ArrayList<Double>()
                        val speed1 = ArrayList<Double>()

                        for (got in power) {
                            if (got.key.toInt() in 0..23)
                                power1.add(got.value)
                        }

                        for (got in speed) {
                            if (got.key.toInt() in 0..23)
                                speed1.add(got.value)
                        }

                        allotGraph(dayOne, power1, speed1)

                        fetch1.setImageResource(R.drawable.sun_selected)
                        fetch2.setImageResource(R.drawable.sun_not_selected)
                        fetch3.setImageResource(R.drawable.sun_not_selected)

                    }
                    else {
                        Toast.makeText(this@AnalysisActivity, "Not able to Draw", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            })

            progressBar.visibility = View.GONE
        }

        fetchDay2.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            val service =
                RetrofitClientInstance.retrofitInstance?.create(GetPointsService::class.java)
            val call = service?.getAllPoints()

            call?.enqueue(object : Callback<PointsList> {
                override fun onFailure(call: Call<PointsList>, t: Throwable) {
                    Toast.makeText(this@AnalysisActivity, "Error Reading JSON", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<PointsList>?, response: Response<PointsList>?) {

                    val body = response?.body()
                    val points = body?.points

                    if (points != null) {

                        val power = points.power
                        val speed = points.speed

                        val power2 = ArrayList<Double>()
                        val speed2 = ArrayList<Double>()

                        for (got in power) {
                            if (got.key.toInt() in 24..47)
                                power2.add(got.value)
                        }

                        for (got in speed) {
                            if (got.key.toInt() in 24..47)
                                speed2.add(got.value)
                        }

                        allotGraph(dayTwo, power2, speed2)

                        fetch1.setImageResource(R.drawable.sun_not_selected)
                        fetch2.setImageResource(R.drawable.sun_selected)
                        fetch3.setImageResource(R.drawable.sun_not_selected)

                    }
                    else {
                        Toast.makeText(this@AnalysisActivity, "Not able to Draw", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            })

            progressBar.visibility = View.GONE
        }

        fetchDay3.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            val service =
                RetrofitClientInstance.retrofitInstance?.create(GetPointsService::class.java)
            val call = service?.getAllPoints()

            call?.enqueue(object : Callback<PointsList> {
                override fun onFailure(call: Call<PointsList>, t: Throwable) {
                    Toast.makeText(this@AnalysisActivity, "Error Reading JSON", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<PointsList>?, response: Response<PointsList>?) {

                    val body = response?.body()
                    val points = body?.points

                    if (points != null) {

                        val power = points.power
                        val speed = points.speed

                        val power3 = ArrayList<Double>()
                        val speed3 = ArrayList<Double>()

                        for (got in power) {
                            if (got.key.toInt() in 48..71)
                                power3.add(got.value)
                        }

                        for (got in speed) {
                            if (got.key.toInt() in 48..71)
                                speed3.add(got.value)
                        }

                        allotGraph(dayThree, power3, speed3)

                        fetch1.setImageResource(R.drawable.sun_not_selected)
                        fetch2.setImageResource(R.drawable.sun_not_selected)
                        fetch3.setImageResource(R.drawable.sun_selected)

                    }
                    else {
                        Toast.makeText(this@AnalysisActivity, "Not able to Draw", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            })

            progressBar.visibility = View.GONE
        }

        backButton?.setOnClickListener {
            startActivity(Intent(this@AnalysisActivity, MainActivity::class.java))
        }

        Toast.makeText(this, "Select any day by tapping the date icon!", Toast.LENGTH_LONG).show()
    }

    fun allotGraph(
        day: String,
        power: ArrayList<Double>,
        speed: ArrayList<Double>
    ) {
        val dataSet: ArrayList<Entry> = ArrayList<Entry>()
        var time = 0

        for (node in power) {
            dataSet.add(Entry(time.toFloat(), node.toFloat()))
            time += 1
        }

        day_text.text = day
        day_text_speed.text = day

        val lineDataSet = LineDataSet(dataSet, "Output Power")
        val iLineDataSet: ArrayList<ILineDataSet> = ArrayList<ILineDataSet>()
        iLineDataSet.add(lineDataSet)

        lineDataSet.color = R.color.colorPrimaryDark
        lineDataSet.setCircleColor(Color.BLACK)
        lineDataSet.valueTextColor = Color.RED

        val max = power.max() ?:0
        val pos = power.indexOf(max)
        val date = calenderInstance.time
        var currentDate = dateFormat.format(date)
        currentDate += if (pos < 10)
            " 0$pos:00"
        else
            " $pos:00"
        val currentTimeWithDate = dateTimeFormat.parse(currentDate)
        val currentTime = timeFormat.format(currentTimeWithDate!!)
        var pow = max.toString().toFloat()
        pow /= 1000

        result.text = "It is observed that maximum output power is at $currentTime which is $pow KWatt."

        val lineData: LineData = LineData(iLineDataSet)
        graph.data = lineData
        graph.invalidate()

        Toast.makeText(
            this@AnalysisActivity,
            "Pinch to Zoom in Graph",
            Toast.LENGTH_SHORT
        ).show()

        recyclerWindSpeed.adapter = WindSpeedAdapter(power, speed)
    }
}