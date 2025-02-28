package com.example.aitarotapplication.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.R
import com.example.aitarotapplication.models.tarotCard

class resultCardAdapter(
    private val selectedCards: List<tarotCard>
) : RecyclerView.Adapter<resultCardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val frontView: View = view.findViewById(R.id.frontView)
        val backView: View = view.findViewById(R.id.backView)
        val cardMessageText: TextView = view.findViewById(R.id.resultMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_result_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = selectedCards[position]
        holder.frontView.visibility = View.VISIBLE
        holder.backView.visibility = View.GONE
        holder.cardMessageText.text = card.cardMessage
        holder.itemView.setOnClickListener {
            flipCard(holder.itemView) {
                // Swap the views at the midpoint: hide front, show back.
                holder.frontView.visibility = View.GONE
                holder.backView.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = selectedCards.size
    private fun flipCard(view: View, onFlipMidway: () -> Unit) {
        // Animate the view's Y-axis from 0° to 90°.
        val flipOut = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f).apply {
            duration = 250
            interpolator = AccelerateInterpolator()
        }
        // Animate from -90° to 0°.
        val flipIn = ObjectAnimator.ofFloat(view, "rotationY", -90f, 0f).apply {
            duration = 250
            interpolator = DecelerateInterpolator()
        }
        flipOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                onFlipMidway() // Swap the content at midpoint
                flipIn.start()
                view.isClickable = false
            }
        })

        AnimatorSet().apply {
            playSequentially(flipOut, flipIn)
            start()
        }
    }
}
