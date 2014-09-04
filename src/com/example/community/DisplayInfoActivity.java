package com.example.community;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DisplayInfoActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
   
      //绑定XML中的ListView，作为Item的容器  
        ListView soldAndQiuGou = (ListView) findViewById(R.id.soldAndQiuGou); 
        Button talkToHeButton = (Button) findViewById(R.id.talkToHeButton);
        Button focusHeORButton = (Button) findViewById(R.id.focusHeORButton);
        
        talkToHeButton.setOnClickListener(new talkToHeButtonListener());
        focusHeORButton.setOnClickListener(new focusHeORButtonListener());
          
        //生成动态数组，并且转载数据  
        ArrayList<HashMap<String, Object>> soldAndQiuGouList = new ArrayList<HashMap<String, Object>>();  
         
        HashMap<String, Object> map1 = new HashMap<String, Object>();  
        map1.put("ItemImage", R.drawable.no_pic);  
        map1.put("ItemTitle", "正在出售");  
        soldAndQiuGouList.add(map1);  
        
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("ItemImage", R.drawable.no_pic);  
        map2.put("ItemTitle", "正在求购");  
        soldAndQiuGouList.add(map2);
        
        //生成适配器，数组===》ListItem  
        SimpleAdapter soldAndQiuGouSchedule = new SimpleAdapter(this, //没什么解释  
        		                                    soldAndQiuGouList,//数据来源   
                                                    R.layout.ding_dan_manage,//ListItem的XML实现  
                                                      
                                                    //动态数组与ListItem对应的子项          
                                                    new String[] {"ItemImage", "ItemTitle"},   
                                                      
                                                    //ListItem的XML文件里面的两个TextView ID  
                                                    new int[] {R.id.ItemImage,R.id.ItemTitle});  
        //添加并且显示  
        soldAndQiuGou.setAdapter(soldAndQiuGouSchedule); 
    }
    
    public class talkToHeButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    public class focusHeORButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    }



}
