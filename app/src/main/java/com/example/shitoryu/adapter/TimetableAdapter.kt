package com.example.shitoryu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shitoryu.R
import com.example.shitoryu.activities.TimetableActivity.Companion.selectedPosition
import com.example.shitoryu.model.EventData
import com.example.shitoryu.model.OnItemClickedListener


class TimetableAdapter(private val list: List<EventData>) :
    RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {

    private var listener: OnItemClickedListener? = null

    fun setOnItemClickedListener(listener: OnItemClickedListener?) {
        this.listener = listener
    }

    @SuppressLint("CutPasteId")
    class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEventDate: TextView
        val radioButton: RadioButton

        init {
            radioButton = itemView.findViewById<RadioButton>(R.id.radioButton)
            tvEventDate = itemView.findViewById<TextView>(R.id.tvEventDate)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        return TimetableViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: TimetableViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val item = list[position]

        if (item.isCompetition != -1) {
            holder.tvEventDate.text =
                "${item.dayOfMonth}.${item.month}.${item.year} - в ${item.hour}:${item.minute}"

            holder.radioButton.isChecked = selectedPosition == position
            holder.radioButton.setOnClickListener {
                notifyDataSetChanged()
                if (listener != null) {
                    listener!!.onItemClicked(position)
                }
            }
        } else {
            holder.radioButton.visibility = View.GONE
            holder.tvEventDate.visibility = View.GONE
        }
    }
}