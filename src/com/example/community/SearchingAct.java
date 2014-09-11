package com.example.community;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchingAct extends Activity {
	    private Spinner searchType = null; 
	    private TextView historyTV=null;
	    private EditText searching_content=null;
	    private Button searching_bt=null;
	    private Button delete_history=null;

	    ArrayAdapter<String> searchTypeAdapter = null; 
	    

	    
	    private String[] searchTypeArray = new String[] {"商品","服务","用户"};

	    
	    private ListView mylistview = null;
		private String[]  mData;
	  
	 
	    private void set_searchType()
	    {        
	    	searchType = (Spinner)findViewById(R.id.searching_search_type);
	    	searchTypeAdapter = new ArrayAdapter<String>(SearchingAct.this,
	                android.R.layout.simple_spinner_item, searchTypeArray);
	    	searchType.setAdapter(searchTypeAdapter);
	    	searchType.setSelection(0,true);  
	    	searchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	        {
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0)
	            {

	            }

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub   arg2为当前选项 0-2
				
				}
	         });
	    }
	    private void delete_history(){
	    	SharedPreferences sp = getSharedPreferences("network_url", 0);  
	    	sp.edit().clear().commit();
	    }
	    
	    
	    private String[] getData() {
	    	SharedPreferences sp = getSharedPreferences("network_url", 0);  
	        String longhistory = sp.getString("history", "");  
	        String[] histories=new String[50];
	        if(longhistory==""){
	        	histories[0]="nothing";

 	        }else{
 	        	histories = longhistory.split(",");  
 	        }
	        
	        // 只保留最近的50条的记录  
	        if (histories.length > 50) {  
	            String[] newHistories = new String[50];  
	            System.arraycopy(histories, 0, newHistories, 0, 50);  
	        }  
	        return histories;
		}
		
	    private void saveHistory(String field) {  
	    	searching_content=(EditText)findViewById(R.id.searching_editText);
	        String text = searching_content.getText().toString();  
	        if(text!=""){
	        	SharedPreferences sp = getSharedPreferences("network_url", 0);  
	 	        String longhistory = sp.getString(field, "");  
	 	        if (!longhistory.contains(text + ",")) {  
	 	            StringBuilder sb = new StringBuilder(longhistory);  
	 	            sb.insert(0, text + ",");  
	 	            sp.edit().putString("history", sb.toString()).commit();  
	 	        }  
	        }
	       
	    }  
	    
		public class MyAdapter extends BaseAdapter{
			private LayoutInflater mInflater;
			public MyAdapter(Context context){
				this.mInflater = LayoutInflater.from(context);
			}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mData.length;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				convertView = mInflater.inflate(R.layout.classify_list, null);
				historyTV = (TextView) convertView.findViewById(R.id.classify_list_bigselect);
				historyTV.setText((String)mData[position]);
				delete_history=(Button)findViewById(R.id.delect_history);
				delete_history.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						delete_history();
						mData=getData();
						notifyDataSetChanged();
						
					}
				});
				
				return convertView;
			}
			
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_searching);
		set_searchType();
		mylistview = (ListView)findViewById(R.id.searching_listview);
		final MyAdapter adapter = new MyAdapter(this);
		mData=getData();
		mylistview.setAdapter(adapter);
		searching_bt=(Button)findViewById(R.id.searching_button);
		searching_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub\
				saveHistory("history");
				Intent intent=new Intent(SearchingAct.this,SearchPageAct.class);
				startActivity(intent);
				finish();
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
}
