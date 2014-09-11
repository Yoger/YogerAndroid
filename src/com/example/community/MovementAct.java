package com.example.community;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MovementAct extends Activity {
    private List<Map<String, Object>> mData;
    private PullToRefreshListView mPullRefreshListView;
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "G1");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "G2");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("img",  R.drawable.ic_launcher);
		list.add(map);
		
		
		return list;
	}
			
	
	public final class ViewHolder{
		public ImageView img;
		public TextView title;
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
				
				convertView = mInflater.inflate(R.layout.movement_list, null);
				holder.img = (ImageView)convertView.findViewById(R.id.movement_image);
				holder.title = (TextView)convertView.findViewById(R.id.movement_text);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String)mData.get(position).get("title"));

			holder.img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(MovementAct.this,DisplayInfoActivity.class);
					 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					 startActivity(intent);		
				}
			});
			
			
			return convertView;
		}
		
	}
///scroll 嵌套 listview	
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;
		if (listAdapter.getCount() <= 1)
			return;

		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, null, listView);
			view.measure(0,0);
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

    protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_movement);
		 mData = getData();
		 MyAdapter adapter = new MyAdapter(this);
		 mPullRefreshListView=(PullToRefreshListView)findViewById(R.id.moment_fresh_listview);
		 ListView actualListView = mPullRefreshListView.getRefreshableView();
		 mPullRefreshListView.setAdapter(adapter);
		 setListViewHeightBasedOnChildren(actualListView);
		 mPullRefreshListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);
		 mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
//				mListItems.removeAll(mListItems);
//				mListItems.addAll(Arrays.asList(upOptions));
//				arrayAdpt.notifyDataSetChanged();
				System.out.println("onPullDownToRefresh");
				Toast.makeText(MovementAct.this, "onPullDownToRefresh!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
//				mListItems.addAll(Arrays.asList(downOptions));
//				arrayAdpt.notifyDataSetChanged();
				System.out.println("onPullUpToRefresh");
				Toast.makeText(MovementAct.this, "onPullUpToRefresh!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}
		});
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				Toast.makeText(MovementAct.this, "end!", Toast.LENGTH_SHORT).show();
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



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movement, menu);
		return true;
	}

}
