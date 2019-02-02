package com.lordcodes.lazyviewbindingarticle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lordcodes.lazyviewbindingarticle.lazyfragment.PlanningFragmentActivity

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, PlanningFragmentActivity::class.java)
        startActivity(intent)
    }
}