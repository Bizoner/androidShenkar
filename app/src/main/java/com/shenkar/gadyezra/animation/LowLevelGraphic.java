package com.shenkar.gadyezra.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shenkar.gadyezra.R;

public class LowLevelGraphic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LowLevelView(this));
    }
}
