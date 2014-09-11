package com.example.community;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NewsCountAct extends Activity {

	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_focus);
	   
	      //掳贸露篓XML脰脨碌胫ListView拢卢脳梅脦陋Item碌胫脠脻脝梅  
	        ListView focusListView = (ListView) findViewById(R.id.focusListView); 
	       
	          focusListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
//			    System.out.println("1");
//			    Toast.makeText(getApplicationContext(), "create!", 2000).show();
					System.out.println("1111");
				Intent intent =new Intent(NewsCountAct.this,ChatActivity.class);
				startActivity(intent);
					
				}
			});
	        ArrayList<HashMap<String, Object>> focusList = new ArrayList<HashMap<String, Object>>();  
	         
	        HashMap<String, Object> map1 = new HashMap<String, Object>();  
	        map1.put("ItemImage", R.drawable.no_pic);  
	        map1.put("ItemTitle", "小布");  
	        focusList.add(map1);  
	        
	        HashMap<String, Object> map2 = new HashMap<String, Object>();  
	        map2.put("ItemImage", R.drawable.no_pic);  
	        map2.put("ItemTitle", "胖子");  
	        focusList.add(map2);
	        
	        SimpleAdapter focusListSchedule = new SimpleAdapter(this, 
	        		                                    focusList,
	                                                    R.layout.ding_dan_manage,//ListItem碌胫XML脢碌睃脰  
	                                                      
	                                                    //露炉脤卢脢媒脳茅脱毛ListItem露脭脱娄碌胫脳脱睃卯          
	                                                    new String[] {"ItemImage", "ItemTitle"},   
	                                                      
	                                                    //ListItem碌胫XML脦胫录镁脌茂脙忙碌胫脕陆赂枚TextView ID  
	                                                    new int[] {R.id.ItemImage,R.id.ItemTitle});  
	        //脤铆录脱虏垄脟脪睃脭脢戮  
	        focusListView.setAdapter(focusListSchedule); 
	    
	        
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

}
