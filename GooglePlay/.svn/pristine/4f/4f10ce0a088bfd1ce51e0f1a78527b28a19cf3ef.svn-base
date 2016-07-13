package com.itheima.googleplay.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.googleplay.domain.AppInfo;
import com.itheima.googleplay.tools.BitmapHelper;
import com.itheima.googleplay.tools.ViewUtils;
import com.itheima.googleplay.view.LoadingPage;
import com.itheima.googleplay.view.LoadingPage.LoadResult;
import com.lidroid.xutils.BitmapUtils;

public abstract class BaseFragment extends Fragment {

	private LoadingPage loadingPage;
	protected BitmapUtils bitmapUtils;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		bitmapUtils = BitmapHelper.getBitmapUtils();
		
		if (loadingPage == null) {  // 之前的frameLayout 已经记录了一个爹了  爹是之前的ViewPager 
			loadingPage = new LoadingPage(getActivity()){

				@Override
				public View createSuccessView() {
					return BaseFragment.this.createSuccessView();
				}

				@Override
				protected LoadResult load() {
					return BaseFragment.this.load();
				}
			};
		}else{
			ViewUtils.removeParent(loadingPage);// 移除frameLayout之前的爹
		}
	
		return loadingPage;  //  拿到当前viewPager 添加这个framelayout  
	}
	/***
	 *  创建成功的界面
	 * @return
	 */
	public  abstract View createSuccessView();
	/**
	 * 请求服务器
	 * @return
	 */
	protected abstract LoadResult load();

	public void show(){
		if(loadingPage!=null){
			loadingPage.show();
		}
	}
	
	
	/**校验数据 */
	public LoadResult checkData(List datas) {
		if(datas==null){
			return LoadResult.error;//  请求服务器失败
		}else{
			if(datas.size()==0){
				return LoadResult.empty;
			}else{
				return LoadResult.success;
			}
		}
		
	}


}
