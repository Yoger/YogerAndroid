package com.example.community;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * 阏奉亜鐣炬稊澶屾暰瀵ゅ绱濈€圭偟骞囨稉锟藉▎钬冲涧濠婃垵濮╂稉锟藉钟叉禈阎楀浄绱濋弮钟冲劵阉拷
 * 
 * @author Feng
 * 
 */
@SuppressWarnings("deprecation")
class MyGallery extends Gallery {

	private RecommendAct recommendAct;

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		int kEvent;
		if (isScrollingLeft(e1, e2)) {
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);

		if (this.getSelectedItemPosition() == 0) {// 鐎圭偟骞囬崥搴拷锟介崝镡诲厣
			this.setSelection(recommendAct.picture.length);
		}
		return false;

	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		System.out.println(" f2 = "+e2.getX()+"  f1 ="+e1.getX());
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

}