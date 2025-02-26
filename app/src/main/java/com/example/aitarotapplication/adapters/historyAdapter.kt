package com.example.aitarotapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.R
import com.example.aitarotapplication.data.Reading
import com.example.aitarotapplication.ui.HistoryDetailsActivity
import com.example.aitarotapplication.ui.ResultActivity
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(
    private val readings: List<Reading>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionText: TextView = view.findViewById(R.id.question)
        val timeText: TextView = view.findViewById(R.id.time)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val reading = readings[position]
                    // Launch ResultActivity, passing the Reading data.
                    val context = view.context
                    val intent = Intent(context, HistoryDetailsActivity::class.java)
                    // Reading must implement Serializable or Parcelable. (See note below.)
                    intent.putExtra("READING", reading)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        val reading = readings[position]
        holder.questionText.text = reading.userQuestion

        // Format the timestamp into a readable date/time string.
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        holder.timeText.text = sdf.format(Date(reading.readingDate))
    }

    override fun getItemCount(): Int = readings.size
}
