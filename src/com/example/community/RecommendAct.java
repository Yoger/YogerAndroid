package com.example.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class RecommendAct extends Activity {
private List<Map<String, Object>> mData;
public int[] picture = { R.drawable.adver1, R.drawable.adver2,
		R.drawable.adver3, R.drawable.adver4 };

private MyGallery pictureGallery = null;
private int index = 0;
private PullToRefreshListView mPullRefreshListView;
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title1", "G1");
		map.put("img1", R.drawable.no_pic);
		map.put("title2", "G2");
		map.put("img2", R.drawable.no_pic);
		list.add(map);

		
		return list;
	}
			
	
	public final class ViewHolder{
		public ImageView img1;
		public TextView title1;
		public ImageView img2;
		public TextView title2;
	}
	
	
	public class MyAdapter extends BaseAdapter{

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
			
			ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				convertView = mInflater.inflate(R.layout.recommend_list, null);
				holder.img1 = (ImageView)convertView.findViewById(R.id.imageView1);
				holder.title1 = (TextView)convertView.findViewById(R.id.textView1);
				holder.img2 = (ImageView)convertView.findViewById(R.id.imageView2);
				holder.title2 = (TextView)convertView.findViewById(R.id.textView2);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.img1.setBackgroundResource((Integer)mData.get(position).get("img1"));
			holder.title1.setText((String)mData.get(position).get("title1"));
			holder.img2.setBackgroundResource((Integer)mData.get(position).get("img2"));
			holder.title2.setText((String)mData.get(position).get("title2"));
            
			holder.img1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(RecommendAct.this,GoodsDetailAct.class);
					 startActivity(intent);		
				}
			});
			holder.img2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(RecommendAct.this,GoodsDetailAct.class);
					 startActivity(intent);		
				}
			});

			
			
			
			return convertView;
		}
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend);
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		mPullRefreshListView=(PullToRefreshListView)findViewById(R.id.recommend_listview);
		mPullRefreshListView.setAdapter(adapter);
		pictureGallery = (MyGallery) findViewById(R.id.mygallery);
		ImageAdapter imAdapter = new ImageAdapter(this);
		pictureGallery.setAdapter(imAdapter);
		Timer timer = new Timer();
		timer.schedule(task, 4000, 4000);
	    mPullRefreshListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
//				mListItems.removeAll(mListItems);
//				mListItems.addAll(Arrays.asList(upOptions));
//				arrayAdpt.notifyDataSetChanged();
				System.out.println("onPullDownToRefresh");
				Toast.makeText(RecommendAct.this, "onPullDownToRefresh!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
//				mListItems.addAll(Arrays.asList(downOptions));
//				arrayAdpt.notifyDataSetChanged();
				System.out.println("onPullUpToRefresh");
				Toast.makeText(RecommendAct.this, "onPullUpToRefresh!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}
		});
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				Toast.makeText(RecommendAct.this, "end!", Toast.LENGTH_SHORT).show();
//				mListItems.add("new data from bottom");
//				arrayAdpt.notifyDataSetChanged();
			}
		});
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

	@SuppressLint("HandlerLeak")
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommend, menu);
		return true;
	}

}
