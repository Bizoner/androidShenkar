package com.shenkar.gadyezra.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LowLevelView extends View implements View.OnClickListener {

    private int newPos;
    private Rect rectangle;
    private Paint paint;
    private int radius = 10;
//    private int mPivotX = 0;
//    private int mPivotY = 0;
    private boolean firstDraw = true;
    private Canvas mCanvas;
    private int viewWidth = getWidth();
    private int viewHeight = getHeight();
    private List<Ball> mBalls = new ArrayList<>();
    private int left = 400;
    private int right = 700;
    private int top = 400;
    private int bottom = 900;

    public LowLevelView(Context context) {
        super(context);
        // create a rectangle that we'll draw later
        rectangle = new Rect(left, top, right, bottom);

        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.BLUE);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        viewWidth = getWidth();
        viewHeight = getHeight();
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rectangle, paint);
//        canvas.drawCircle(mPivotX, mPivotY, radius, paint);

            for (int i = 0; i < mBalls.size(); i++) {
                canvas.drawCircle(mBalls.get(i).x,mBalls.get(i).y,radius,mBalls.get(i).color);
            }

//        startAnimation();
    }

    @Override
    public void onClick(View v) {
        createBall();
        startAnimation();
        Log.v("tag","Clicked");
    }

    private void createBall() {
        int minX = radius * 2;
        int maxX = getWidth() - (radius *2 );
        int minY = radius * 2;
        int maxY = getHeight() - (radius *2 );

        Random random = new Random();
        int mPivotX = random.nextInt(maxX - minX + 1) + minX;
        int mPivotY = random.nextInt(maxY - minY + 1) + minY;
        while (mPivotX > left && mPivotX < right && mPivotY > top && mPivotY < bottom) {
            mPivotX = random.nextInt(maxX - minX + 1) + minX;
            mPivotY = random.nextInt(maxY - minY + 1) + minY;
        }
        Paint ballPaint = new Paint();
        ballPaint.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        mBalls.add(new Ball(mPivotX,mPivotY,ballPaint));
    }

    public void startAnimation() {

        ValueAnimator animator = ValueAnimator.ofInt(10, 10);
        animator.setDuration(100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                for (int i = 0; i < mBalls.size(); i++) {
                    if ((mBalls.get(i).y > viewHeight) && mBalls.get(i).up) {
                        mBalls.get(i).up = false;
                    }
                    if ((mBalls.get(i).y <= 0) && !mBalls.get(i).up) {
                        mBalls.get(i).up = true;
                    }
                    if ((mBalls.get(i).x > viewWidth) && mBalls.get(i).right) {
                        mBalls.get(i).right = false;
                    }
                    if ((mBalls.get(i).x <= 0) && !mBalls.get(i).right) {
                        mBalls.get(i).right = true;
                    }

                    if ((mBalls.get(i).y > top) && (mBalls.get(i).y < bottom) && (mBalls.get(i).x > left) && (mBalls.get(i).x < right)) {
                            mBalls.get(i).up = !mBalls.get(i).up;
                            mBalls.get(i).right = !mBalls.get(i).right;
                    }

                    if (mBalls.get(i).up) {
                        mBalls.get(i).updateLocation(0,value);
                    } else {
                        mBalls.get(i).updateLocation(0,0 -value);
                    }
                    if (mBalls.get(i).right) {
                        mBalls.get(i).updateLocation(value,0);
                    } else {
                        mBalls.get(i).updateLocation(0 - value,0);
                    }
                }
                invalidate();
            }
        });
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();

    }
}
