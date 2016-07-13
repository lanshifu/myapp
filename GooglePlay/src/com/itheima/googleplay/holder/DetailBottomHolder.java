package com.itheima.googleplay.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.itheima.googleplay.R;
import com.itheima.googleplay.domain.AppInfo;
import com.itheima.googleplay.tools.UiUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class DetailBottomHolder extends BaseHolder<AppInfo> implements OnClickListener {
	@ViewInject(R.id.bottom_favorites)
	Button bottom_favorites;
	@ViewInject(R.id.bottom_share)
	Button bottom_share;
	@ViewInject(R.id.progress_btn)
	Button progress_btn;
	@Override
	public View initView() {
		View view=UiUtils.inflate(R.layout.detail_bottom);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		bottom_favorites.setOnClickListener(this);
		bottom_share.setOnClickListener(this);
		progress_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bottom_favorites:
			Toast.makeText(UiUtils.getContext(), " ’≤ÿ", 0).show();
			break;
		case R.id.bottom_share:
			Toast.makeText(UiUtils.getContext(), "∑÷œÌ", 0).show();
			break;
		case R.id.progress_btn:
			Toast.makeText(UiUtils.getContext(), "œ¬‘ÿ", 0).show();
			break;
		}
	}

}
