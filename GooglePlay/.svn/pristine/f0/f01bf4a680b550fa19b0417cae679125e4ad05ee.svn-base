package com.itheima.googleplay.fragment;

import java.util.List;

import com.itheima.googleplay.adapter.DefaultAdapter;
import com.itheima.googleplay.domain.CategoryInfo;
import com.itheima.googleplay.holder.BaseHolder;
import com.itheima.googleplay.holder.CategoryContentHolder;
import com.itheima.googleplay.protocol.CategoryProtocol;
import com.itheima.googleplay.tools.UiUtils;
import com.itheima.googleplay.view.BaseListView;
import com.itheima.googleplay.view.LoadingPage.LoadResult;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryFragment extends BaseFragment {
	private List<CategoryInfo> datas;
	public static int ITEM_TITLE =2;

	// 创建成功的界面
	@Override
	public View createSuccessView() {
		BaseListView listView = new BaseListView(UiUtils.getContext());
		listView.setAdapter(new CategoryAdapter(datas, listView));

		return listView;
	}

	private class CategoryAdapter extends DefaultAdapter<CategoryInfo> {
		private int position;// 当前条目位置记录

		public CategoryAdapter(List<CategoryInfo> datas, ListView lv) {
			super(datas, lv);
		}

		// 实现每个条目的界面
		@Override
		protected BaseHolder<CategoryInfo> getHolder() {
			if (!datas.get(position).isTitle()) {
				return new CategoryContentHolder();
			}else{
				return new CategoryTitleHolder();
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			this.position = position;
			return super.getView(position, convertView, parent);
		}

		@Override
		protected boolean hasMore() { // 当前方法 如果为false onload就不会被调用了
			return false;
		}

		@Override
		protected int getInnerItemViewType(int position) {
			if (datas.get(position).isTitle()) {
				return ITEM_TITLE;
			} else {
				return super.getInnerItemViewType(position);
			}
		}

		@Override
		protected List<CategoryInfo> onload() {
			return null;
		}
		//  集合 管理三个convertView   
		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount() + 1; // 又额外多了一种条目类型  现在又三种  1 标题 2 内容 3 加载更多(没有显示)
		}

	}

	// 请求服务器
	@Override
	protected LoadResult load() {
		CategoryProtocol protocol = new CategoryProtocol();
		datas = protocol.load(0);
		return checkData(datas);
	}
}
