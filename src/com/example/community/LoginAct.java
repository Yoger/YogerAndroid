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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAct extends Activity {

	private Button mLoginBn=null;
	private Button mRegisterBn=null;
	private EditText mUserEditText=null;
	private EditText mPwdEditText=null;
	private SharedPreferences sp;
	public static  int userID;
//	private JSONArray result;
	private String url="http://192.168.0.103:8080/login.json";
	private String userAccount;
	private String userPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUserEditText=(EditText)findViewById(R.id.txtUser);
		mPwdEditText=(EditText)findViewById(R.id.txtPwd);
		mLoginBn = (Button)findViewById(R.id.btnLogin);
		mLoginBn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				userAccount=mUserEditText.getText().toString();
				userPassword=mPwdEditText.getText().toString();
				if(userAccount.isEmpty()||userPassword.isEmpty()){
					Toast.makeText(getApplicationContext(), "用户名/密码不能为空！", Toast.LENGTH_SHORT).show();
				}
				else{
					Map<String, String> loginMap = new HashMap<String, String>();  
					loginMap.put("userAccount", userAccount);
					loginMap.put("userPassword", userPassword);
					volleyUser(LoginAct.this,url, new JSONObject(loginMap));
					
				}
				
			}
		});
		mRegisterBn = (Button)findViewById(R.id.btnRegister);
		mRegisterBn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(LoginAct.this,RegisterAct.class);
				startActivity(intent);
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
			askForOut();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void askForOut() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("确定退出").setMessage("确定退出？").setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, 
							int which) {
						finish();
					}
				}).setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, 
							int which) {
						dialog.cancel();
					}
				}).setCancelable(false).show();

	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	public  void volleyUser(Context con,String url,JSONObject jsonObject){
		RequestQueue mQueue=Volley.newRequestQueue(con);
		MyJsonArrayRequest mMyJsonArrayRequest=new MyJsonArrayRequest(url, jsonObject, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub
				System.out.println(response.toString());
		        try {
					JSONObject result=(JSONObject)response.get(0);
					System.out.println(result.toString());
					if(result.has("ok")){
						userID=result.getInt("userID");
						LocalValue.setUserID(userID);
						SaveInfo(userAccount,userID,userPassword);
						Intent intent=new Intent(LoginAct.this,MainPageAct.class);
						startActivity(intent);
						finish();
					}
					else{
						Toast.makeText(getApplicationContext(), "用户名/密码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
						
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
				Toast.makeText(getApplicationContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
				System.out.println("myJSReq--json receive failed!");
//				Toast.makeText(getApplicationContext(), "连接服务器失败！", Toast.LENGTH_SHORT).show();
			}
		});
		mQueue.add(mMyJsonArrayRequest); 
		
	}
	public void SaveInfo(String userAccount,int userID,String userPwd){
		sp=this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		Editor ed=sp.edit();
		ed.putString("userAccount", userAccount);
		ed.putString("userPassword", userPwd);
		ed.putInt("userID", userID);
		ed.commit();
		
	}

}
