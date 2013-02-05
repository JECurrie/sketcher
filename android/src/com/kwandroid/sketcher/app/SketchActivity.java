package com.kwandroid.sketcher.app;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;

import com.kwandroid.sketcher.R;

public class SketchActivity extends Activity {
	
	int INITIAL_COLOR = Color.BLACK;

	private SketchView mSketchView;
	private Bitmap mBitmap;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.sketch);
        mSketchView = (SketchView) findViewById(R.id.view_sketch);
		mBitmap = Bitmap.createBitmap(800, 600, Config.ARGB_8888);
		mBitmap.eraseColor(Color.WHITE);
        mSketchView.setBitmap(mBitmap);
        LayoutParams layoutParams = mSketchView.getLayoutParams();
        layoutParams.width = mBitmap.getWidth();
        layoutParams.height = mBitmap.getHeight();
        mSketchView.setLayoutParams(layoutParams);
    }
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
//		outState.putInt(Extras.COLOR, mSketchView.getColor());
//		outState.putParcelable(Extras.BITMAP, mSketchView.getBitmap());
//		outState.putInt(Extras.ROTATION, mRotation);
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			// TODO: Menu
		    return true;
		case KeyEvent.KEYCODE_BACK:
			finish();
		    return true;
	    default:
		    break;
		}
	    return false;
	}

}
