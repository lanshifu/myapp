package com.itheima.googleplay.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;

import com.itheima.googleplay.DetailActivity;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.ListBaseAdapter;
import com.itheima.googleplay.domain.AppInfo;
import com.itheima.googleplay.holder.HomePictureHolder;
import com.itheima.googleplay.protocol.HomeProtocol;
import com.itheima.googleplay.tools.UiUtils;
import com.itheima.googleplay.view.BaseListView;
import com.itheima.googleplay.view.LoadingPage.LoadResult;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

public class HomeFragment extends BaseFragment {
	private List<AppInfo> datas;
	private List<String> pictures; //  顶部ViewPager 显示界面的数据
	
	// 当Fragment挂载的activity创建的时候调用
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		show();
	}

	public View createSuccessView() {
		BaseListView listView=new BaseListView(UiUtils.getContext());
		HomePictureHolder holder=new HomePictureHolder();
		holder.setData(pictures);
		View contentView = holder.getContentView(); // 得到holder里面管理的view对象
		//contentView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		listView.addHeaderView(contentView); // 把holder里的view对象 添加到listView的上面
		
		
		listView.setAdapter(new ListBaseAdapter(datas,listView){

			@Override
			protected List<AppInfo> onload() {
				HomeProtocol protocol=new HomeProtocol();
				List<AppInfo> load = protocol.load(datas.size());
				datas.addAll(load);
				return load;
			}

		
			
		});
	
		// 第二个参数 慢慢滑动的时候是否加载图片 false  加载   true 不加载
		//  第三个参数  飞速滑动的时候是否加载图片  true 不加载 
		listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		bitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);  // 设置如果图片加载中显示的图片
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);// 加载失败显示的图片

		return listView;
	}

	
	public LoadResult load() {
		HomeProtocol protocol=new HomeProtocol();
		datas = protocol.load(0);  // load index 从哪个位置开始获取数据   0  20  40 60 
		pictures = protocol.getPictures();
		return checkData(datas);
	}
	


}
