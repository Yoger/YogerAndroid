package com.example.community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private boolean flag_star = false;
    private ImageView collect_image = null;
    private boolean flag_collect_image = false;
    private ImageView share_image = null;
    private boolean flag_share_image = false;

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
				if(!flag_star){
					star.setImageDrawable(getResources().getDrawable(R.drawable.star_good));
					flag_star = true;
					Toast.makeText(getApplicationContext(), "已点赞", Toast.LENGTH_SHORT).show();
				}
				else{
					star.setImageDrawable(getResources().getDrawable(R.drawable.star_bad));
					flag_star = false;
					Toast.makeText(getApplicationContext(), "已取消赞", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		collect_image = (ImageView) findViewById(R.id.collect_image);
		
		collect_goods.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!flag_collect_image){
					collect_image.setImageDrawable(getResources().getDrawable(R.drawable.card_icon_favorite_highlighted));
					flag_collect_image = true;
					Toast.makeText(getApplicationContext(), "已收藏", Toast.LENGTH_SHORT).show();
				}
				else{
					collect_image.setImageDrawable(getResources().getDrawable(R.drawable.card_icon_favorite));
					flag_collect_image = false;
					Toast.makeText(getApplicationContext(), "已取消收藏", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		share_image = (ImageView) findViewById(R.id.share_image);
		
		share_goods.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!flag_share_image){
					share_image.setImageDrawable(getResources().getDrawable(R.drawable.guide_tfaccount_on));
					flag_share_image = true;
					Toast.makeText(getApplicationContext(), "已分享", Toast.LENGTH_SHORT).show();
				}
				else{
					share_image.setImageDrawable(getResources().getDrawable(R.drawable.guide_tfaccount_nm));
					flag_share_image = false;
					Toast.makeText(getApplicationContext(), "已取消分享", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		message_goods.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GoodsDetailAct.this,MessageActivity.class);
				startActivity(intent);
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
