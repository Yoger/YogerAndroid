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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterAct extends Activity {
	private EditText userAccountEd=null;
	private EditText userPwdEd=null;
	private EditText userNameEd=null;
	private Button commitBn=null;
	private String userAccount;
	private String userName;
	private String userPwd;
    private String url=LocalValue.getUrl()+"register.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		userAccountEd=(EditText)findViewById(R.id.edit_register_account);
		userPwdEd=(EditText)findViewById(R.id.edit_register_password);
		userNameEd=(EditText)findViewById(R.id.edit_register_username);
		commitBn=(Button)findViewById(R.id.button_register_commite);
		commitBn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userAccount=userAccountEd.getText().toString();
				userPwd=userPwdEd.getText().toString();
				userName=userNameEd.getText().toString();
				if(userAccount.isEmpty()||userName.isEmpty()||userPwd.isEmpty()){
					
					Toast.makeText(getApplicationContext(), "输入不能为空！", Toast.LENGTH_SHORT).show();
				}
				else{
					Map<String, Object> infoMap = new HashMap<String, Object>();  
//					infoMap.put("userID", LocalValue.getUserID());
					infoMap.put("userAccount", userAccount);
					infoMap.put("userName", userName);
					infoMap.put("userPassword", userPwd);
					volleyUser(RegisterAct.this,url, new JSONObject(infoMap));
					
				}
				
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
		getMenuInflater().inflate(R.menu.register, menu);
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
                    	Toast.makeText(getApplicationContext(), "注册成功！即将跳转到登录", 2000).show();
                    	try {
							Thread.currentThread();
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	finish();
                    }
                    else{
                    	String error=result.getString("error");
                    	Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
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
