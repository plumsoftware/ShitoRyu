package com.example.shitoryu.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.shitoryu.R
import com.example.shitoryu.adapter.SpinnerAdapter
import com.example.shitoryu.model.EventData

class EventActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val event: EventData? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("event", EventData::class.java)
        } else {
            intent.getParcelableExtra("event")
        }

        val textViewDate = findViewById<TextView>(R.id.textViewDate)
        val spinnerPlace = findViewById<Spinner>(R.id.spinnerPlace)
        val textViewGroup = findViewById<TextView>(R.id.textViewGroup)
        val expandButton = findViewById<ImageButton>(R.id.expandButton)
        val cardInfo = findViewById<CardView>(R.id.cardInfo)
        var isExpanded = true

        textViewDate.text = "${event?.dayOfMonth}.${event?.month}.${event?.year}"
        textViewGroup.text = event?.group

        val list = listOf(
            event!!.place,
            "Красный пахарь 7 (временно)"
        )

        val adapter = SpinnerAdapter(list)
        spinnerPlace.adapter = adapter

        expandButton.setOnClickListener{
            if (isExpanded) {
                cardInfo.visibility = View.GONE
                expandButton.setImageResource(R.drawable.baseline_arrow_drop_up_24)
                isExpanded = false
            } else {
                cardInfo.visibility = View.VISIBLE
                expandButton.setImageResource(R.drawable.baseline_arrow_drop_down_24)
                isExpanded = true
            }

        }
    }
}