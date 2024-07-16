package com.example.firmadigital.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class draw_signature extends View {
    public static boolean isTouched = false;

    private Paint paint = new Paint();
    private Path path = new Path();
    private Bitmap bitmap;
    private Canvas canvas;

    public draw_signature(Context context) {
        super(context);
        init(null, 0);
    }

    public draw_signature(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public draw_signature(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public draw_signature(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet attrs, int defStyle) {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(0xFF000000); // Set the default color to black
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouched = true;
                path.moveTo(xPos, yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos, yPos);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paint);
                //path.reset();
                break;
            default:
                return false;
        }

        invalidate(); // Redraw
        return true;
    }

    public void clearCanvas() {
        path.reset();
        canvas.drawColor(0xFFFFFFFF); // Clear with white color
        invalidate();
    }

    public Bitmap getSignatureBitmap() {

        return bitmap;
    }
}
