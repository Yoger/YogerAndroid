package com.example.community;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FlowPageAct extends Activity {
	
  private ImageView goodsRelease=null;
  private ImageView seeksRelease=null;
  private ImageView close=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow);
		setTheme(R.style.Transparent);
		goodsRelease=(ImageView)findViewById(R.id.goods_release);
		seeksRelease=(ImageView)findViewById(R.id.seek_release);
		close=(ImageView)findViewById(R.id.flow_close);
		goodsRelease.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FlowPageAct.this,ReleaseGoodsAct.class);
				startActivity(intent);
				
			}
		});
		seeksRelease.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FlowPageAct.this,ReleaseSeekAct.class);
				startActivity(intent);
				
			}
		});
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
//		LinearLayout v = (LinearLayout)findViewById(R.id.flow_empty);//找到你要设透明背景的layout 的id
//		v.setBackgroundColor(color.transparent);
//		v.setAlpha(255);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flow_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
