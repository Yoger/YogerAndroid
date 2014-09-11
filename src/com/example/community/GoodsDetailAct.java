package com.example.community;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GoodsDetailAct extends Activity {
	
	
    private ImageView star = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goods_detail);
		
		LinearLayout collect_goods = (LinearLayout) findViewById(R.id.collect_goods);
		LinearLayout share_goods = (LinearLayout) findViewById(R.id.share_goods);
		LinearLayout message_goods = (LinearLayout) findViewById(R.id.message_goods);
		
		star = (ImageView) findViewById(R.id.bad_point);
		
		star.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				star.setImageDrawable(getResources().getDrawable(R.drawable.star_good));
			}
			
		});

		
		collect_goods.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "已收藏", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		share_goods.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "已分享", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		message_goods.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "已留言", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		Button GoodsDetail_contact = (Button) findViewById(R.id.GoodsDetail_Button_contact);
		
		GoodsDetail_contact.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "小布的初夜", Toast.LENGTH_SHORT).show();
			}
		});
		
		 Button btnBack = (Button) findViewById(R.id.TitleBackBtn);

			btnBack.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					KeyEvent newEvent = new KeyEvent
							(KeyEvent.ACTION_DOWN,
							KeyEvent.KEYCODE_BACK);
					onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);
				}
			});
		}
		
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
			{
				// 按下的如果是BACK，同时没有重复
				finish();

				return true;
			}

			return super.onKeyDown(keyCode, event);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_detail, menu);
		return true;
	}

}
