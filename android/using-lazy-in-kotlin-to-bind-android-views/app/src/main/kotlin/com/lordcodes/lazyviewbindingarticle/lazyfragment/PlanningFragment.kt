package com.lordcodes.lazyviewbindingarticle.lazyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lordcodes.lazyviewbindingarticle.R

class PlanningFragment : Fragment() {
    private var counter = 1

    private val planningText: TextView by bindView(R.id.planning_text)
    private val launchButton: Button by bindView(R.id.launch_button)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planningText.text = "onCreateView $counter"
        counter += 1

        launchButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragment_container,
                    SecondFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}
