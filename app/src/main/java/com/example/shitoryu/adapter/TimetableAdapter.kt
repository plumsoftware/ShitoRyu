package com.example.shitoryu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shitoryu.R
import com.example.shitoryu.activities.TimetableActivity
import com.example.shitoryu.model.EventData
import com.example.shitoryu.model.OnItemClickedListener


class TimetableAdapter(private val list: List<EventData>) :
    RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {

    private var listener: OnItemClickedListener? = null

//    fun setOnItemClickedListener(listener: OnItemClickedListener?) {
//        this.listener = listener
//    }

    @SuppressLint("CutPasteId")
    class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEventDate: TextView
        val tvTitle: TextView
        val checkBox: CheckBox

        init {
            checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
            tvEventDate = itemView.findViewById<TextView>(R.id.tvEventDate)
            tvTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
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

        if (position == 0) {
            holder.tvTitle.visibility = View.VISIBLE
            holder.tvTitle.text = "Расписание занятий"
        } else {
            holder.tvTitle.visibility = View.GONE
        }

        if (position == 5) {
            holder.tvTitle.visibility = View.VISIBLE
            holder.tvTitle.text = "Расписание соревнований"
        } else {
            holder.tvTitle.visibility = View.GONE
        }

        //next element
//        val nextItem = if ((position + 1) != list.size) {
//            list[position + 1]
//        } else {
//            null
//        }
//
//        if (nextItem != null) {
//            if (position == 0) {
//                holder.tvTitle.text = "Расписание занятий"
//            } else {
//                holder.tvTitle.visibility = View.GONE
//
//                if (item.isCompetition == nextItem.isCompetition){
//                    holder.tvTitle.visibility = View.GONE
//                } else {
//                    holder.tvTitle.visibility = View.VISIBLE
//                    holder.tvTitle.text = "Расписание соревнований"
//                }
//            }
//        } else {
//            holder.tvTitle.visibility = View.GONE
//        }

        holder.tvEventDate.text =
            "${item.dayOfMonth}.${item.month}.${item.year} - в ${item.hour}:${item.minute}"

//        holder.checkBox.isChecked = selectedPosition == position
        holder.checkBox.setOnClickListener {
            TimetableActivity.selectedEvent = item
//            notifyDataSetChanged()
//            if (listener != null) {
//                listener!!.onItemClicked(position)
//            }
        }
    }
}