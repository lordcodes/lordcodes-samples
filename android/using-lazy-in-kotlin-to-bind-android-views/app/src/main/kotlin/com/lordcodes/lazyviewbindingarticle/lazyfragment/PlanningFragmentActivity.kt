package com.lordcodes.lazyviewbindingarticle.lazyfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lordcodes.lazyviewbindingarticle.R

class PlanningFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_planning_fragment)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,
                PlanningFragment()
            )
            .addToBackStack(null)
            .commit()
    }
}