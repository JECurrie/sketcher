package com.kwandroid.sketcher.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class SketchView extends ImageView {
	
    private Bitmap  mBitmap;
    private Canvas  mCanvas;
    private Paint   mPaint;
    private Paint   mBitmapPaint;
    private Path    mPath;
    
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

	public SketchView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(8);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPath = new Path();
	}
    
    private void touchDown(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touchUp(float x, float y) {
    	mCanvas.drawPoint(x, y, mPaint);
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp(x, y);
                invalidate();
                break;
        }
        return true;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
    }
    
    public void setBitmap(Bitmap bitmap) {
		mBitmap = bitmap.copy(bitmap.getConfig(), true);
        mCanvas = new Canvas(mBitmap);
    }
    
    public Bitmap getBitmap() {
		return mBitmap;
    }
    
    public void setColor(int color) {
        mPaint.setColor(color);
    }
    
    public int getColor() {
        return mPaint.getColor();
    }
}

