package com.bruce.designer.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.designer.R;
import com.bruce.designer.adapter.GridAdapter;
import com.bruce.designer.adapter.ViewPagerAdapter;
import com.bruce.designer.model.Album;
import com.bruce.designer.util.ApiUtil;
import com.bruce.designer.util.AppManager;
import com.bruce.designer.util.UiUtil;
import com.bruce.designer.util.cache.ImageLoader;
import com.bruce.designer.view.RoundImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

public class Activity_Main extends BaseActivity {

	private long lastQuitTime = 0;
	private int albumTailId = 0;
	
	private ViewPager viewPager;
	private ListView albumListView;
	private AlbumListAdapter albumListAdapter;
	
	/*Tabs*/
	private View tab1View;
	private View tab2View;
	private View tab3View;
	
	private PullToRefreshListView pullToRefreshView1;
	private PullToRefreshListView pullToRefreshView2;
	
	private Handler tabDataHandler = new Handler(){
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
//					Map<String, Object> dataMap = (Map<String, Object>) msg.obj;
//					if(dataMap!=null){
//						List<Album> albumList = (List<Album>) dataMap.get("albumList");
//						if(albumList!=null&&albumList.size()>0){
//							AlbumListAdapter2 albumListAdapter = new AlbumListAdapter2(context, albumList);
//							albumListView.setAdapter(albumListAdapter);
//						}
//					}
//					break;
//				case 0:
//					break;
					pullToRefreshView1.onRefreshComplete();
					break;
				case 2:
					pullToRefreshView2.onRefreshComplete();
					break;
				default:
					break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tab1View = findViewById(R.id.tab_recommend);
		tab2View = findViewById(R.id.tab_latest);
		tab3View = findViewById(R.id.tab_myfollow);
		
		View[] tabViews = new View[]{tab1View, tab2View, tab3View};
		for(int i=0;i<tabViews.length;i++){
			final int index = i;
			View clickedTab = tabViews[i];
			if(clickedTab!=null){
				clickedTab.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						viewPager.setCurrentItem(index);
					}
				});
			}
		}
		
		
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		List<View> pagerViews = new ArrayList<View>();
		// 初始化引导图片列表
		View tabContentView1 = inflater.inflate(R.layout.albums_listview, null);
		View tabContentView2 = inflater.inflate(R.layout.albums_listview, null);
		
		//构造tab1
		List<String> dataList = new ArrayList<String>();
		dataList.add("test");
		dataList.add("test");
		dataList.add("test");
		pullToRefreshView1 = (PullToRefreshListView) tabContentView1.findViewById(R.id.pull_refresh_list);
		pullToRefreshView1.setMode(Mode.BOTH);
		pullToRefreshView1.setOnRefreshListener(tab1RefreshListener);
		ListView listView1 = pullToRefreshView1.getRefreshableView();
		listView1.setAdapter(new AlbumListAdapter(context, dataList, 0));
		
		//构造tab2
		List<String> dataList2 = new ArrayList<String>();
		dataList2.add("test");
		dataList2.add("test");
		pullToRefreshView2 = (PullToRefreshListView) tabContentView2.findViewById(R.id.pull_refresh_list);
		pullToRefreshView2.setMode(Mode.BOTH);
		pullToRefreshView2.setOnRefreshListener(tab2RefreshListener);
		ListView listView2 = pullToRefreshView2.getRefreshableView();
		listView2.setAdapter(new AlbumListAdapter(context, dataList2, 1));
		
		
		pagerViews.add(tabContentView1);
		pagerViews.add(tabContentView2);
		
		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(pagerViews, this);
		viewPager.setAdapter(viewPagerAdapter);

//		albumListView = (ListView) findViewById(R.id.main_album_list);

	}
	
	
	class AlbumListAdapter extends BaseAdapter {

		private List<String> albumList;
		private Context context;
		private int style;
		
		public AlbumListAdapter(Context context, List<String> albumList, int style) {
			this.context = context;
			this.albumList = albumList;
			this.style = style;
		}

		@Override
		public int getCount() {
			if (albumList != null) {
				return albumList.size();
			}
			return 0;
		}

		@Override
		public String getItem(int position) {
			if (albumList != null) {
				return albumList.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(getItem(position)!=null){
				View albumItemView = null;
				
				if(style==1){//grid mode
					albumItemView = LayoutInflater.from(context).inflate(R.layout.item_album_view2, null);
					
					GridView gridView = (GridView) albumItemView.findViewById(R.id.grid);
					gridView.setAdapter(new GridAdapter(context));
					
				}else{//mainImg mode
					albumItemView = LayoutInflater.from(context).inflate(R.layout.item_album_view, null);
					ImageView coverView = (ImageView) albumItemView.findViewById(R.id.imgPic);
					ImageLoader.loadImage("http://www.jinwanr.com.cn/staticFile/image/20140306/large/100012_151a51d814179c4526cba469afde4f99.jpg", coverView);
				}
						
				ImageView avatarView = (ImageView) albumItemView.findViewById(R.id.avatar);
				ImageLoader.loadImage("http://img.jinwanr.com.cn/staticFile/avatar/default.jpg", avatarView);
				
				TextView usernameView = (TextView) albumItemView.findViewById(R.id.txtUsername);
				usernameView.setText("大树珠宝");
				
				TextView pubtimeView = (TextView) albumItemView.findViewById(R.id.txtTime);
				pubtimeView.setText("半小时前");
				
				TextView titleView = (TextView) albumItemView.findViewById(R.id.txtSticker);
				titleView.setText("标题");
				TextView contentView = (TextView) albumItemView.findViewById(R.id.txtContent);
				contentView.setText("内容");
				
				
				return albumItemView;
			}
			return null;
		}
	}
	
	
	
	
	class AlbumListAdapter2 extends BaseAdapter {

		private List<Album> albumList;
		private Context context;

		public AlbumListAdapter2(Context context, List<Album> albumList) {
			this.context = context;
			this.albumList = albumList;
		}

		@Override
		public int getCount() {
			if (albumList != null) {
				return albumList.size();
			}
			return 0;
		}

		@Override
		public Album getItem(int position) {
			if (albumList != null) {
				return albumList.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView==null){
				convertView = LayoutInflater.from(context).inflate(R.layout.item_album, null);
			}
			
			Album album = getItem(position);
			RoundImageView designerAvatarView = (RoundImageView) convertView.findViewById(R.id.item_album_designer_avatar);
			RoundImageView albumImageView = (RoundImageView) convertView.findViewById(R.id.item_album_thumb_img);
			
			//加载内容图片
			ImageLoader.getInstance().loadImage(album.getCoverMediumImg(), albumImageView); 
			return convertView;
		}
	}
	
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean flag = false;
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 退出
			long currentTime = System.currentTimeMillis();
			if (lastQuitTime <= 0 || currentTime - lastQuitTime > 2000) {
				lastQuitTime = System.currentTimeMillis();
				UiUtil.showShortToast(context, "再次点击后即可退出应用");
			} else {
				AppManager.getInstance().exitApp(context);
			}
		} else {
			flag = super.onKeyUp(keyCode, event);
		}
		return flag;
	}
	
	
	OnRefreshListener2<ListView> tab1RefreshListener = new OnRefreshListener2<ListView>() {
		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			Toast.makeText(getApplicationContext(), "下拉刷新", Toast.LENGTH_LONG).show();
			//TODO 启动线程获取数据
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
//					Map<String, Object> dataMap = ApiUtil.getAlbumList(albumTailId);
//					Message message;
//					if(dataMap!=null){
//						message = handler.obtainMessage(1);
//						message.obj = dataMap;
//					}else{
//						message = handler.obtainMessage(0);
//					}
//					message.sendToTarget();
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tabDataHandler.obtainMessage(1).sendToTarget();
				}
			});
			thread.start();
		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			Toast.makeText(getApplicationContext(), "上拉获取更多", Toast.LENGTH_LONG).show();
			//TODO 启动线程获取数据
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tabDataHandler.obtainMessage(1).sendToTarget();
				}
			});
			thread.start();
		}
	};
	
	OnRefreshListener2<ListView> tab2RefreshListener = new OnRefreshListener2<ListView>() {
		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			Toast.makeText(getApplicationContext(), "下拉刷新", Toast.LENGTH_LONG).show();
			//TODO 启动线程获取数据
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tabDataHandler.obtainMessage(2).sendToTarget();
				}
			});
			thread.start();
		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			Toast.makeText(getApplicationContext(), "上拉获取更多", Toast.LENGTH_LONG).show();
			//TODO 启动线程获取数据
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tabDataHandler.obtainMessage(2).sendToTarget();
				}
			});
			thread.start();
		}
	};
}
