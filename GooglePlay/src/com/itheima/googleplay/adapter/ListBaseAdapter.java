package com.itheima.googleplay.adapter;

import java.util.List;

import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima.googleplay.DetailActivity;
import com.itheima.googleplay.domain.AppInfo;
import com.itheima.googleplay.holder.BaseHolder;
import com.itheima.googleplay.holder.ListBaseHolder;
import com.itheima.googleplay.tools.UiUtils;

public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {
	public ListBaseAdapter(List<AppInfo> datas,ListView listView) {
		super(datas,listView);
	}

	@Override
	protected BaseHolder<AppInfo> getHolder() {
		return new ListBaseHolder();
	}

	@Override
	protected abstract List<AppInfo> onload();
	
	
	@Override
	public void onInnerItemClick(int position) {
		super.onInnerItemClick(position);
		Toast.makeText(UiUtils.getContext(), "position:"+position, 0).show();
		AppInfo appInfo = datas.get(position);
		Intent intent=new Intent(UiUtils.getContext(), DetailActivity.class);
		intent.putExtra("packageName", appInfo.getPackageName());
		UiUtils.startActivity(intent);
	}
}
