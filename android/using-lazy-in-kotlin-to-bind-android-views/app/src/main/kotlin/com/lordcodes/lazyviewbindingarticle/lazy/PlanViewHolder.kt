package com.lordcodes.lazyviewbindingarticle.lazy

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lordcodes.lazyviewbindingarticle.R

class PlanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val planningText: TextView by bindView(R.id.planning_text)
}