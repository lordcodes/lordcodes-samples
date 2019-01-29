package com.lordcodes.lazyviewbindingarticle.lazy

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lordcodes.lazyviewbindingarticle.R

class PlanningActivity : AppCompatActivity() {
//    private val planningText by lazy {
//        findViewById<TextView>(R.id.planning_text)
//    }
//    private val planningText by lazy(LazyThreadSafetyMode.NONE) {
//        findViewById<TextView>(R.id.planning_text)
//    }
//    private val planningText by bindView<TextView>(R.id.planning_text)
    private val planningText: TextView by bindView(R.id.planning_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_planning)

        planningText.text = "Hello!"
    }
}