package com.example.shitoryu.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shitoryu.R
import com.example.shitoryu.model.EventData

class CalendarAdapter(
    private val list: List<EventData>
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {


    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDate: TextView
        val card: CardView

        init {
            textDate = itemView.findViewById(R.id.date)
            card = itemView.findViewById(R.id.card)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.calendar_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val item = list[position]

        val color =
            if ((position + 1) == item.dayOfMonth) {
                Color.parseColor("#4FCC92")
                if (item.isCompetition == 1) Color.parseColor("#C71E46") else Color.parseColor("#4FCC92")
            } else Color.parseColor("#BDBCBC")

        holder.textDate.text = (position + 1).toString()
        holder.card.setCardBackgroundColor(color)
    }
}