﻿package com.example.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ChooseClassifyAct extends Activity {   
    private List<String> mListData;
    private ListView ls=null;
  	private String Act; 
  	private ArrayList<HashMap<String, Object>> lstImageItem = null;
    private List<String> getListData() {
		List<String> list = new ArrayList<String>();
        list.add("衣服");
        list.add("裤子");
        list.add("交通工具");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
		
		return list;
	}
	
	public class MyListAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		public MyListAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mListData.size();
		}
        @Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView bigSelect=null;
			if (convertView == null) { 
				convertView = mInflater.inflate(R.layout.classify_list, null);
		    }
			bigSelect = (TextView)convertView.findViewById(R.id.classify_list_bigselect);
			bigSelect.setText((String)mListData.get(position));
		    ls=(ListView)findViewById(R.id.choose_classfy_listview);
		    ls.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					switch(arg2){
					case 0:
						lstImageItem = new ArrayList<HashMap<String, Object>>();
						for(int i=0;i<10;i++)  
					      {  
					        HashMap<String, Object> map = new HashMap<String, Object>();  
					        map.put("ItemImage", R.drawable.bre);//添加图像资源的ID   
					        map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText   
					        lstImageItem.add(map);  
					      }  
						setGridView1();
						break;
					case 1:
						lstImageItem = new ArrayList<HashMap<String, Object>>();
						for(int i=0;i<10;i++)  
					      {  
					        HashMap<String, Object> map = new HashMap<String, Object>();  
					        map.put("ItemImage", R.drawable.lunch);//添加图像资源的ID   
					        map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText   
					        lstImageItem.add(map);  
					      } 
						setGridView1();
						break;
						default:
							lstImageItem = new ArrayList<HashMap<String, Object>>();
							for(int i=0;i<10;i++)  
						      {  
						        HashMap<String, Object> map = new HashMap<String, Object>();  
						        map.put("ItemImage", R.drawable.dinner);//添加图像资源的ID   
						        map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText   
						        lstImageItem.add(map);  
						      }
							setGridView1();
							break;
							
					}
					
				
					
				}
		    	
		    });		
			return convertView;
		}
		
	}
      
/////gridview
	
 public void setGridView1(){
	  MyGridView gridview = (MyGridView) findViewById(R.id.choose_classify_gridView1);   
      //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应   
      SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释   
                                                lstImageItem,//数据来源    
                                                R.layout.gridview,//night_item的XML实现   
                                                 //动态数组与ImageItem对应的子项           
                                                new String[] {"ItemImage","ItemText"},   
                                                 //ImageItem的XML文件里面的一个ImageView,两个TextView ID   
                                                new int[] {R.id.Grid_ItemImage,R.id.Grid_ItemText});  
      //添加并且显示   
      gridview.setAdapter(saImageItems);  
      //添加消息处理   
      gridview.setOnItemClickListener(new ItemClickListener1());  
      }  
  class  ItemClickListener1 implements OnItemClickListener  
  {  
       public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened    
                                  View arg1,//The view within the AdapterView that was clicked   
                                  int arg2,//The position of the view in the adapter   
                                  long arg3//The row id of the item that was clicked   
                                  ) {  
    //在本例中arg2=arg3   
    HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
    //显示所选Item的ItemText   
    setTitle((String)item.get("ItemText"));  
    finish();
}  
       
}
 
	
	
/////gridview

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choose_classify);
		mListData = getListData();
		MyListAdapter adapter = new MyListAdapter(this);
		ls=(ListView)findViewById(R.id.choose_classfy_listview);
		ls.setAdapter(adapter);
		setGridView1();
        Bundle b=this.getIntent().getExtras();
        Act=b.getString("Act");
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
