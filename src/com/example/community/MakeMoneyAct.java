package com.example.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.community.cache.LruImageCache;
import com.community.local.LocalValue;
import com.community.volley.MyJsonArrayRequest;
import com.example.adapter.ViewPagerAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MakeMoneyAct extends Activity {


	private PullToRefreshListView mPullRefreshListView;
	private List<Map<String,Object>>  mData;
	public int[] picture = { R.drawable.adv5, R.drawable.ad6,
			R.drawable.ad7jpg, R.drawable.ad8 };
	private MakeMoneyAdapter listAdapter =null;
	private String url=LocalValue.getUrl()+"makemoney.json";
	private Runnable viewpagerRunnable;
	private static Handler handler;
	private ViewPager viewpager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		listAdapter = new MakeMoneyAdapter(this);
		setContentView(R.layout.activity_make_money);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		mPullRefreshListView = (PullToRefreshListView)findViewById(R.id.make_money_listview);
		mPullRefreshListView.setAdapter(listAdapter);
		initViewPager();
		GetNetData();
		mPullRefreshListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				System.out.println("onPullDownToRefresh");
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				System.out.println("onPullUpToRefresh");
				new GetDataTask().execute();
			}
		});
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				Toast.makeText(MakeMoneyAct.this, "end!", Toast.LENGTH_SHORT).show();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);
	}
	public void initViewPager(){
		handler = new Handler();
		initListener();
		int len = picture.length;
		View view = null;
		ImageView imageview;
		LayoutInflater inflater = LayoutInflater.from(this);
		List<View> lists = new ArrayList<View>();
		for (int i = 0; i < len; i++)
		{
			view = inflater.inflate(R.layout.imageview_layout, null);
			imageview = (ImageView) view.findViewById(R.id.imageview);
			imageview.setBackgroundResource(picture[i]);
			lists.add(view);
		}
		viewpager.setAdapter(new ViewPagerAdapter(lists));
		initRunnable();
	}
	private void initListener()
	{
		viewpager.setOnPageChangeListener(new OnPageChangeListener()
		{
			boolean isScrolled = false;

			@Override
			public void onPageScrollStateChanged(int status)
			{
				switch (status)
				{
				case 1:// ���ƻ���
					isScrolled = false;
					break;
				case 2:// �����л�
					isScrolled = true;
					break;
				case 0:// ��������

					if (viewpager.getCurrentItem() == viewpager.getAdapter()
							.getCount() - 1 && !isScrolled)
					{
						viewpager.setCurrentItem(0);
					}
					else if (viewpager.getCurrentItem() == 0 && !isScrolled)
					{
						viewpager.setCurrentItem(viewpager.getAdapter()
								.getCount() - 1);
					}
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageSelected(int index)
			{
//				index_textview.setText("the " + (index + 1) + " page");
			}
		});
	}

	private static final int TIME = 4500;

	/**
	 * ��ʱ�л�
	 */
	protected void initRunnable()
	{
		viewpagerRunnable = new Runnable()
		{

			@Override
			public void run()
			{
				int nowIndex = viewpager.getCurrentItem();
				int count = viewpager.getAdapter().getCount();
				// �����һ�ŵ������������һ�ţ����л�����һ��
				if (nowIndex + 1 >= count)
				{
					viewpager.setCurrentItem(0);
				} else
				{
					viewpager.setCurrentItem(nowIndex + 1);
				}
				handler.postDelayed(viewpagerRunnable, TIME);
			}
		};
		handler.postDelayed(viewpagerRunnable, TIME);
	}

	public void  GetNetData(){
		RequestQueue mQueue=Volley.newRequestQueue(MakeMoneyAct.this);
		Map<String, String> mmMap = new HashMap<String, String>();  
		mmMap.put("userID", "1");
		MyJsonArrayRequest mMyJsonArrayRequest=new MyJsonArrayRequest(url, new JSONObject(mmMap), new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub
				mData.clear();
				for(int i=0;i<response.length();i++){
					JSONObject t;
					try {
						t = (JSONObject)response.get(i);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("userName", t.getString("userName"));
						map.put("goodsName", t.getString("goodsName"));
						map.put("avater", t.getString("avater"));
						map.put("userID", t.getString("userID"));
						mData.add(map);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				mPullRefreshListView.setAdapter(listAdapter);
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
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("userName", "使劲加载中...");
		map.put("goodsName","使劲加载中...");
		map.put("avater", LocalValue.getUrl()+"pic/nopic.png");
		list.add(map);
		
		
		return list;
	}
	
	/**
	 *  PullToReFresh后台操作函数。
	 * 
	 */
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

	public final class ViewHolder{
		public NetworkImageView img;
		public TextView title;
		public TextView info;
		//public Button viewBtn;
	}
	public class MakeMoneyAdapter extends BaseAdapter{
		
		
		//LayoutInflater 用于一个没有被载入的界面的载入
		private LayoutInflater mInflater;
		public MakeMoneyAdapter(Context context){
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
				holder.img = (NetworkImageView) convertView.findViewById(R.id.make_money_img);
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
			LruImageCache lruImageCache = LruImageCache.instance();
			RequestQueue mQueue=Volley.newRequestQueue(MakeMoneyAct.this);
			ImageLoader imageLoader = new ImageLoader(mQueue,lruImageCache);  
			holder.img.setDefaultImageResId(R.drawable.no_pic);  
			holder.img.setErrorImageResId(R.drawable.no_pic);          
			holder.img.setImageUrl((String)mData.get(position).get("avater"), imageLoader);  
			holder.title.setText((String)mData.get(position).get("userName"));
			holder.info.setText((String)mData.get(position).get("goodsName"));
			return convertView;
		}
		
	}

}
