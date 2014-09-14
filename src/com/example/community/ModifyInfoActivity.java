package com.example.community;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rt.BASE64Decoder;
import com.rt.BASE64Encoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ModifyInfoActivity extends Activity {
	
	private LinearLayout switchAvatar;
	private ImageView faceImage;
	File tempFile=null;
	String imagePath=null;
	String newFileUrl="/data/data/com.example.community/cache/newava.jpg";

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_modify_info);
	        
	        switchAvatar = (LinearLayout) findViewById(R.id.head_image_ll);
			faceImage = (ImageView) findViewById(R.id.imageOfUser);
			// 设置事件监听
			switchAvatar.setOnClickListener(listener);
	        
	        Button changeImageButton = (Button) findViewById(R.id.changeImageButton);
	        Button changePSWButton = (Button) findViewById(R.id.changePSWButton);
	        Button saveChangeButton = (Button) findViewById(R.id.saveChangeButton);
	        EditText userName = (EditText) findViewById(R.id.userName);
	        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
	        EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
	      //绑定XML中的ListView，作为Item的容器  
	          
	        changeImageButton.setOnClickListener(new changeImageButtonListener());
	        changePSWButton.setOnClickListener(new changePSWButtonListener());
	        saveChangeButton.setOnClickListener(new saveChangeButtonListener());
	        
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

	
	    
	    public class changeImageButtonListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();
			}
	    	
	    }
	    
	    public class saveChangeButtonListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
	    	
	    }
	    
	    public class changePSWButtonListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ModifyInfoActivity.this,ChangePswAct.class);
				startActivity(intent);

				
			}
	    	
	    }
	    
	    
	    
	    /******头像设置*****/
	    
	    
	    /* 组件 */

		private String[] items = new String[] { "选择本地图片", "拍照" };
		/* 头像名称 */
		private static final String IMAGE_FILE_NAME = "faceImage.jpg";
		/*裁剪之后头像默认名称*/
		private static final String IMAGE_AVATAR = "avatar.jpg";

		/* 请求码 */
		private static final int IMAGE_REQUEST_CODE = 0;
		private static final int CAMERA_REQUEST_CODE = 1;
		private static final int RESULT_REQUEST_CODE = 2;

		private View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog();
			}
		};

		/**
		 * 显示选择对话框
		 */
		private void showDialog() {

			new AlertDialog.Builder(this)
					.setTitle("设置头像")
					.setItems(items, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case 0:
								Intent intentFromGallery = new Intent();
								intentFromGallery.setType("image/*"); // 设置文件类型
								intentFromGallery
										.setAction(Intent.ACTION_GET_CONTENT);
								startActivityForResult(intentFromGallery,
										IMAGE_REQUEST_CODE);
								break;
							case 1:

								Intent intentFromCapture = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								// 判断存储卡是否可以用，可用进行存储
								if (Tools.hasSdcard()) {

									intentFromCapture.putExtra(
											MediaStore.EXTRA_OUTPUT,
											Uri.fromFile(new File(Environment
													.getExternalStorageDirectory(),
													IMAGE_FILE_NAME)));
								}

								startActivityForResult(intentFromCapture,
										CAMERA_REQUEST_CODE);
								break;
							}
						}
					})
					.setNegativeButton("取消", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).show();

		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			//结果码不等于取消时候
			if (resultCode != RESULT_CANCELED) {

				switch (requestCode) {
				case IMAGE_REQUEST_CODE:
					startPhotoZoom(data.getData());
					break;
				case CAMERA_REQUEST_CODE:
					if (Tools.hasSdcard()) {
						File tempFile = new File(
								Environment.getExternalStorageDirectory()
										+ "/" +  IMAGE_FILE_NAME);
						startPhotoZoom(Uri.fromFile(tempFile));
					} else {
						Toast.makeText(ModifyInfoActivity.this, "未找到存储卡，无法存储照片！",
								Toast.LENGTH_LONG).show();
					}

					break;
				case RESULT_REQUEST_CODE:
					if (data != null) {
						getImageToView(data);
					}
					break;
				}
			}
			super.onActivityResult(requestCode, resultCode, data);
		}

		/**
		 * 裁剪图片方法实现
		 * 
		 * @param uri
		 */
		public void startPhotoZoom(Uri uri) {

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, "image/*");
			// 设置裁剪
			intent.putExtra("crop", "true");
			// aspectX aspectY 是宽高的比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY 是裁剪图片宽高
			intent.putExtra("outputX", 320);
			intent.putExtra("outputY", 320);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, 2);
		}

		/**
		 * 保存裁剪之后的图片数据
		 * 
		 * @param picdata
		 */
		 private void getImageToView(Intent data) {   
		        Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap photo = extras.getParcelable("data");
//					tempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+ "/" + IMAGE_AVATAR);
					tempFile=new File("/data/data/com.example.community/cache/"+IMAGE_AVATAR);
					BufferedOutputStream bos;
					try {
						bos = new BufferedOutputStream(new FileOutputStream(tempFile));
						photo.compress(Bitmap.CompressFormat.JPEG, 80, bos);
						bos.flush();
						bos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					imagePath=tempFile.getPath();
                    System.out.println(tempFile.getPath());
					Drawable drawable = new BitmapDrawable(getResources(), photo);
					faceImage.setImageDrawable(drawable);
//					try {
//						generateImage(getImageStr(imagePath),newFileUrl);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					upImage();
					
		    }
		 }
		 public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
			//String imgFile = "d:\\111.jpg";// 待处理的图片
			InputStream in = null;
			byte[] data = null;
			// 读取图片字节数组
			try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
			// 对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);// 返回Base64编码过的字节数组字符串
			}
		 public void upImage(){
				String url="http://192.168.0.103:8080/changepassword.json";
				RequestQueue mQueue=Volley.newRequestQueue(this);
				Map<String, Object> appendHeader = new HashMap<String, Object>();
				appendHeader.put("userID", 1);
				appendHeader.put("oldpwd",156);
				appendHeader.put("newpwd", getImageStr(imagePath));
				JsonObjectRequest mRequest=new JsonObjectRequest(Method.POST, url, new JSONObject(appendHeader), new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						System.out.println(response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
				mQueue.add(mRequest);
				
			}
		
		 public static boolean generateImage(String imgStr,String imgFile)throws Exception {// 对字节数组字符串进行Base64解码并生成图片
			 if (imgStr == null) // 图像数据为空
			 return false;
			 BASE64Decoder decoder = new BASE64Decoder();
			 try {
			 // Base64解码
			 byte[] b = decoder.decodeBuffer(imgStr);
			 for (int i = 0; i < b.length; ++i) {
			 if (b[i] < 0) {// 调整异常数据
			 b[i] += 256;
			 }
			 }
			 // 生成jpeg图片
			 String imgFilePath = imgFile;// 新生成的图片
			 OutputStream out = new FileOutputStream(imgFilePath);
			 out.write(b);
			 out.flush();
			 out.close();
			 return true;
			 } catch (Exception e) {
			 throw e;
			 }
			 }
}
