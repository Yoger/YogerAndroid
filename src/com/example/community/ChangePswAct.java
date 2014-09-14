package com.example.community;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.community.local.LocalValue;
import com.community.volley.MyJsonArrayRequest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ChangePswAct extends Activity {
	private Button mConfirmBn=null;
	private EditText oldpwd=null;
	private EditText newpwd=null;
	private EditText newpwd2=null;
	private SharedPreferences sp;
    String url="http://192.168.0.103:8080/changepassword.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LocalValue local=new LocalValue();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_change_psw);
		oldpwd=(EditText)findViewById(R.id.oldpwd_edit);
		newpwd=(EditText)findViewById(R.id.newpwd1_edit);
		newpwd2=(EditText)findViewById(R.id.newpwd2_edit);
		mConfirmBn=(Button)findViewById(R.id.change_psw_confirm);
		mConfirmBn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
				String oldpwdRight=sp.getString("userPassword"," ");
				String oldpwdUser=oldpwd.getText().toString();
				String newpwdUser=newpwd.getText().toString();
				String newpwd2User=newpwd.getText().toString();
				if(oldpwdRight.equals(oldpwdUser)){
					if(newpwdUser.equals(newpwd2User)){
						Editor ed=sp.edit();
						ed.putString("userPassword", newpwdUser);
						ed.commit();
						Map<String, Object> changeMap = new HashMap<String, Object>();  
						changeMap.put("userID", LocalValue.getUserID());
						changeMap.put("oldpwd", oldpwdRight);
						changeMap.put("newpwd", newpwdUser);
						volleyUser(ChangePswAct.this,url, new JSONObject(changeMap));
					}
					else{
						Toast.makeText(getApplicationContext(), "新密码2次输入不一致，请检查！！", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "原密码输入错误，请重新输入！", Toast.LENGTH_SHORT).show();
				}
				
//				finish();
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
		getMenuInflater().inflate(R.menu.change_psw, menu);
		return true;
	}
	public void volleyUser(Context con,String url,JSONObject jsonObject){
		RequestQueue mQueue=Volley.newRequestQueue(con);
		MyJsonArrayRequest mMyJsonArrayRequest=new MyJsonArrayRequest(url, jsonObject, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub
				System.out.println(response.toString());
		        try {
					JSONObject result=(JSONObject)response.get(0);
                    if(result.has("ok")){
                    	Toast.makeText(getApplicationContext(), "密码修改成功！", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    	Toast.makeText(getApplicationContext(), "修改失败！", Toast.LENGTH_SHORT).show();
                    }
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				System.out.println("myJSReq--json receive failed!");
				Toast.makeText(getApplicationContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
			}
		});
		mQueue.add(mMyJsonArrayRequest); 
		
	}

}
