package com.lordcodes.lazyviewbindingarticle.findviewbyid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lordcodes.lazyviewbindingarticle.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlanningActivity extends AppCompatActivity {
    private TextView planningText;
    private ImageView appIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planning);
        // Before: Needed to cast
        planningText = (TextView) findViewById(R.id.planning_text);
        // Now: No longer need to
        appIcon = findViewById(R.id.app_icon);

        planningText.setText("Hello!");
    }
}
