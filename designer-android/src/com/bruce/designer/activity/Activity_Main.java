package com.bruce.designer.activity;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.bruce.designer.R;
import com.bruce.designer.model.Album;
import com.bruce.designer.util.ApiUtil;
import com.bruce.designer.util.AppManager;
import com.bruce.designer.util.UiUtil;
import com.bruce.designer.util.cache.ImageLoader;
import com.bruce.designer.view.RoundImageView;

public class Activity_Main extends BaseActivity {

	private long lastQuitTime = 0;
	private int albumTailId = 0;
	

	private ListView albumListView;
	
	private Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					Map<String, Object> dataMap = (Map<String, Object>) msg.obj;
					if(dataMap!=null){
						List<Album> albumList = (List<Album>) dataMap.get("albumList");
						if(albumList!=null&&albumList.size()>0){
							AlbumListAdapter albumListAdapter = new AlbumListAdapter(context, albumList);
							albumListView.setAdapter(albumListAdapter);
						}
					}
					break;
				case 0:
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

		albumListView = (ListView) findViewById(R.id.main_album_list);
		
		//TODO 启动线程获取数据
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String, Object> dataMap = ApiUtil.getAlbumList(albumTailId);
				Message message;
				if(dataMap!=null){
					message = handler.obtainMessage(1);
					message.obj = dataMap;
				}else{
					message = handler.obtainMessage(0);
				}
				message.sendToTarget();
			}
		});
		thread.start();
	}
	
	
	
	
	class AlbumListAdapter extends BaseAdapter {

		private List<Album> albumList;
		private Context context;

		public AlbumListAdapter(Context context, List<Album> albumList) {
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
			View itemLayout = LayoutInflater.from(context).inflate(R.layout.item_album, null);
			
			Album album = getItem(position);
			RoundImageView designerAvatarView = (RoundImageView) itemLayout.findViewById(R.id.item_album_designer_avatar);
			RoundImageView albumImageView = (RoundImageView) itemLayout.findViewById(R.id.item_album_thumb_img);
			
			//加载内容图片
			ImageLoader.getInstance().loadImage(album.getCoverMediumImg(), albumImageView);
			
			return itemLayout;
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
}
