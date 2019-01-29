package com.lordcodes.lazyviewbindingarticle.kotlinandroidextensions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lordcodes.lazyviewbindingarticle.R

import kotlinx.android.synthetic.main.activity_planning.*

class PlanningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_planning)

        planning_text.text = "Hello"
    }
}