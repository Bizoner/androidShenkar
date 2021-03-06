package com.shenkar.gadyezra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shenkar.gadyezra.animation.LowLevelGraphic;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onCalcClick(View view) {
        startActivity(new Intent(this, Calculator.class));
    }

    public void onBirthdayClick(View view) {
        startActivity(new Intent(this, Birthday.class));
    }

    public void onAnimationClick(View view) {
        startActivity(new Intent(this, LowLevelGraphic.class));
    }
}
