package com.example.community;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_modify_info);
	        
	        switchAvatar = (LinearLayout) findViewById(R.id.head_image_ll);
			faceImage = (ImageView) findViewById(R.id.imageOfUser);
			// �����¼�����
			switchAvatar.setOnClickListener(listener);
	        
	        Button changeImageButton = (Button) findViewById(R.id.changeImageButton);
	        Button changePSWButton = (Button) findViewById(R.id.changePSWButton);
	        Button saveChangeButton = (Button) findViewById(R.id.saveChangeButton);
	        EditText userName = (EditText) findViewById(R.id.userName);
	        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
	        EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
	      //��XML�е�ListView����ΪItem������  
	          
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
				// ���µ������BACK��ͬʱû���ظ�
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
	    
	    
	    
	    /******ͷ������*****/
	    
	    
	    /* ��� */

		private String[] items = new String[] { "ѡ�񱾵�ͼƬ", "����" };
		/* ͷ������ */
		private static final String IMAGE_FILE_NAME = "faceImage.jpg";
		/*�ü�֮��ͷ��Ĭ������*/
		private static final String IMAGE_AVATAR = "avatar.jpg";

		/* ������ */
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
		 * ��ʾѡ��Ի���
		 */
		private void showDialog() {

			new AlertDialog.Builder(this)
					.setTitle("����ͷ��")
					.setItems(items, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case 0:
								Intent intentFromGallery = new Intent();
								intentFromGallery.setType("image/*"); // �����ļ�����
								intentFromGallery
										.setAction(Intent.ACTION_GET_CONTENT);
								startActivityForResult(intentFromGallery,
										IMAGE_REQUEST_CODE);
								break;
							case 1:

								Intent intentFromCapture = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								// �жϴ洢���Ƿ�����ã����ý��д洢
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
					.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).show();

		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			//����벻����ȡ��ʱ��
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
						Toast.makeText(ModifyInfoActivity.this, "δ�ҵ��洢�����޷��洢��Ƭ��",
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
		 * �ü�ͼƬ����ʵ��
		 * 
		 * @param uri
		 */
		public void startPhotoZoom(Uri uri) {

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, "image/*");
			// ���òü�
			intent.putExtra("crop", "true");
			// aspectX aspectY �ǿ�ߵı���
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY �ǲü�ͼƬ���
			intent.putExtra("outputX", 320);
			intent.putExtra("outputY", 320);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, 2);
		}

		/**
		 * ����ü�֮���ͼƬ����
		 * 
		 * @param picdata
		 */
		 private void getImageToView(Intent data) {   
		        Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap photo = extras.getParcelable("data");
					File tempFile = new File(
							Environment
									.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
									+ "/" + IMAGE_AVATAR);
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

					Drawable drawable = new BitmapDrawable(getResources(), photo);
					faceImage.setImageDrawable(drawable);
		    }
		 }
}
