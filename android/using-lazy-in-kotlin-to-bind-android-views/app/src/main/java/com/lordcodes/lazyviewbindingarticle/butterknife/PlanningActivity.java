package com.lordcodes.lazyviewbindingarticle.butterknife;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lordcodes.lazyviewbindingarticle.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanningActivity extends AppCompatActivity {
    @BindView(R.id.planning_text) TextView planningText;
    @BindView(R.id.app_icon) ImageView appIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planning);
        ButterKnife.bind(this);

        planningText.setText("Hello!");
    }
}
