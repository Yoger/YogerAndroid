package com.example.community;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.community.RecommendAct.ImageAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MakeMoneyAct extends Activity {


	private PullToRefreshListView mPullRefreshListView;
	private List<Map<String,Object>>  mData;
	public int[] picture = { R.drawable.adv5, R.drawable.ad6,
			R.drawable.ad7jpg, R.drawable.ad8 };
	private MoneyGalary pictureGallery = null;
	private int index = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		setContentView(R.layout.activity_make_money);
		mPullRefreshListView = (PullToRefreshListView)findViewById(R.id.make_money_listview);
		MyAdapter adapter = new MyAdapter(this);
		mPullRefreshListView.setAdapter(adapter);
		mPullRefreshListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
//				mListItems.removeAll(mListItems);
//				mListItems.addAll(Arrays.asList(upOptions));
//				arrayAdpt.notifyDataSetChanged();
				System.out.println("onPullDownToRefresh");
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
//				mListItems.addAll(Arrays.asList(downOptions));
//				arrayAdpt.notifyDataSetChanged();
				System.out.println("onPullUpToRefresh");
				new GetDataTask().execute();
			}
		});
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				Toast.makeText(MakeMoneyAct.this, "end!", Toast.LENGTH_SHORT).show();
//				mListItems.add("new data from bottom");
//				arrayAdpt.notifyDataSetChanged();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);
		pictureGallery = (MoneyGalary) findViewById(R.id.MoneyGallery);
		ImageAdapter imAdapter = new ImageAdapter(this);
		pictureGallery.setAdapter(imAdapter);
		Timer timer = new Timer();
		timer.schedule(task, 4000, 4000);
		
	}
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			Message message = new Message();
			message.what = 2;
			index = pictureGallery.getSelectedItemPosition();
			index++;
			handler.sendMessage(message);
		}
	};

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 2:
				pictureGallery.setSelection(index);
				break;
			default:
				break;
			}
		}
	};


	/**
	 * 自定义图片显示适配器
	 * 
	 */
	class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(picture[position % picture.length]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(
					Gallery.LayoutParams.FILL_PARENT,
					Gallery.LayoutParams.FILL_PARENT));
			return imageView;
		}
	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "G1");
		map.put("info", "google 1");
		map.put("img", R.drawable.i1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "G2");
		map.put("info", "google 2");
		map.put("img", R.drawable.i2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google 3");
		map.put("img", R.drawable.i3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google 3");
		map.put("img", R.drawable.i3);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google 3");
		map.put("img", R.drawable.i3);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google 3");
		map.put("img", R.drawable.i3);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google 3");
		map.put("img", R.drawable.i3);
		list.add(map);
		return list;
	}

	public void showInfo(){
		new AlertDialog.Builder(this)
		.setTitle("我的listview")
		.setMessage("介绍...")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
		
	}
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
//			mListItems.addFirst("new data from top");
			

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_money, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public final class ViewHolder{
		public ImageView img;
		public TextView title;
		public TextView info;
		//public Button viewBtn;
	}
	
	public class MyAdapter extends BaseAdapter{
		
		
		//LayoutInflater 用于一个没有被载入的界面的载入
		private LayoutInflater mInflater;
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
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
			// TODO Auto-generated method stub
			
			ViewHolder holder = null;
			
			//如果当前View中没用holder 新建并初始化
			if (convertView == null){
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.makemoney_list, null);
				holder.img = (ImageView) convertView.findViewById(R.id.make_money_img);
				holder.title = (TextView) convertView.findViewById(R.id.make_money_title);
				holder.info = (TextView) convertView.findViewById(R.id.make_money_info);
				//holder.viewBtn= (Button) convertView.findViewById(R.id.make_money_view_btn);
				holder.img.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						 Intent intent = new Intent(MakeMoneyAct.this,DisplayInfoActivity.class);
						 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 startActivity(intent);		
					}
				});
				convertView.setTag(holder);
					
			}else{
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String)mData.get(position).get("title"));
			holder.info.setText((String)mData.get(position).get("info"));
			
			/*holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showInfo();					
				}
			});*/
			
			
			return convertView;
		}
		
	}

}
