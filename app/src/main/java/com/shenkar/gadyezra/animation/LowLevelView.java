package com.shenkar.gadyezra.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class LowLevelView extends View implements View.OnClickListener {

    private Rect rectangle;
    private Paint paint;
    private int radius = 10;
    private int mPivotX = 0;
    private int mPivotY = 0;
    private boolean firstDraw = true;
    private Canvas mCanvas;
    public LowLevelView(Context context) {
        super(context);
        int left = 400;
        int right = 700;
        int top = 400;
        int bottom = 900;
        // create a rectangle that we'll draw later
        rectangle = new Rect(left, top, right, bottom);

        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.BLUE);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rectangle, paint);
        canvas.drawCircle(mPivotX, mPivotY, radius, paint);
    }

    @Override
    public void onClick(View v) {
        drawBall();
        Log.v("tag","Clicked");
    }

    private void drawBall() {
        int minX = radius * 2;
        int maxX = getWidth() - (radius *2 );

        int minY = radius * 2;
        int maxY = getHeight() - (radius *2 );
        Random random = new Random();
        mPivotX = random.nextInt(maxX - minX + 1) + minX;
        mPivotY = random.nextInt(maxY - minY + 1) + minY;
        paint = new Paint();
        paint.setColor(Color.RED);
        invalidate();
    }
}
