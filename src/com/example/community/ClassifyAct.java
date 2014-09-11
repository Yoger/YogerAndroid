﻿package com.example.community;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ClassifyAct extends Activity {
	

	    
	    private List<String> mListData;
	    private ListView ls=null;
	    private ImageView classify_search=null;
	    private EditText classify_edittext=null;
	  	 
	 
	//////list

		
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
			    ls=(ListView)findViewById(R.id.classfy_listview);
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
	    
	    
  //////list   
  /////gridview
		
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
	 public void setGridView1(){
		  MyGridView gridview = (MyGridView) findViewById(R.id.classify_gridView1);  
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
	    
	    Intent intent = new Intent(ClassifyAct.this,SoldingGoodsActivity.class);
		startActivity(intent);
	}  
	       
   }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classify);
		mListData = getListData();
		MyListAdapter adapter = new MyListAdapter(this);
		ls=(ListView)findViewById(R.id.classfy_listview);
		ls.setAdapter(adapter);
		
		/*
		 * 
		 * 在刚进入分类界面  GridView初始化数据
		 */
		/*lstImageItem = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<10;i++)  
	      {  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("ItemImage", R.drawable.dinner);//添加图像资源的ID   
	        map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText   
	        lstImageItem.add(map);  
	      }*/
		setGridView1();
		classify_search = (ImageView) findViewById(R.id.clissify_search_iv);
		classify_search.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
               Toast.makeText(ClassifyAct.this,"请先输入搜索内容",500).show();       
			}
			
		});
		classify_edittext = (EditText) findViewById(R.id.classify_edit);
		classify_edittext.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
               Intent intent=new Intent(ClassifyAct.this,SearchingAct.class);
               startActivity(intent);
               
				
			}
			
		});
	}

}
