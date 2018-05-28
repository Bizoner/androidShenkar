package com.shenkar.gadyezra.animation;

import android.graphics.Paint;

public class Ball {
    int x;
    int y;
    boolean up = false;
    boolean right = true;
    Paint color;

    public Ball(int x, int y,Paint color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void updateLocation (int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
        return;
    }
}
