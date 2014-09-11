package com.example.community;


import android.os.Bundle;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class HomePageAct extends ActivityGroup  {
	private LocalActivityManager localActivityManager1 = null;
	//private ScrollView messagesScrollView = null;
	private LinearLayout message=null;
     private ImageView search_iv=null;
     private EditText home_search=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		localActivityManager1 = getLocalActivityManager();
		message = (LinearLayout)findViewById(R.id.messages);
		message.removeAllViews();
		message.addView(localActivityManager1.startActivity("Module1",
				new Intent(HomePageAct.this, RecommendAct.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)).getDecorView());
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				message.removeAllViews();
				message.addView(localActivityManager1.startActivity("Module1",
						new Intent(HomePageAct.this, RecommendAct.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)).getDecorView());
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				message.removeAllViews();
				message.addView(localActivityManager1.startActivity("Module2",
						new Intent(HomePageAct.this, MovementAct.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)).getDecorView());
			}
		});
	

		search_iv = (ImageView) findViewById(R.id.homepage_search_button);
		search_iv.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
               Toast.makeText(HomePageAct.this,"«Îœ» ‰»ÎÀ—À˜ƒ⁄»›",500).show();
               
				
			}
			
		});
		home_search = (EditText) findViewById(R.id.homepage_search_edit);
		home_search.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
               Intent intent=new Intent(HomePageAct.this,SearchingAct.class);
               startActivity(intent);
               
				
			}
			
		});
	

	}
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

}
