package com.itheima.googleplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.domain.AppInfo;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.tools.UiUtils;

public class ListBaseHolder extends BaseHolder<AppInfo> {
	ImageView item_icon;
	TextView item_title,item_size,item_bottom;
	RatingBar item_rating;
	
	public void refreshView(AppInfo data){
		this.item_title.setText(data.getName());// 设置应用程序的名字
		String size=Formatter.formatFileSize(UiUtils.getContext(), data.getSize());
		this.item_size.setText(size);
		this.item_bottom.setText(data.getDes());
		float stars = data.getStars();
		this.item_rating.setRating(stars); // 设置ratingBar的值
		String iconUrl = data.getIconUrl();  //http://127.0.0.1:8090/image?name=app/com.youyuan.yyhl/icon.jpg
		
		// 显示图片的控件
		bitmapUtils.display(this.item_icon, HttpHelper.URL+"image?name="+iconUrl);
	}

	@Override
	public View initView() {
		View contentView=View.inflate(UiUtils.getContext(), R.layout.item_app, null);
		this.item_icon=(ImageView) contentView.findViewById(R.id.item_icon);
		this.item_title=(TextView) contentView.findViewById(R.id.item_title);
		this.item_size=(TextView) contentView.findViewById(R.id.item_size);
		this.item_bottom=(TextView) contentView.findViewById(R.id.item_bottom);
		this.item_rating=(RatingBar) contentView.findViewById(R.id.item_rating);
		return contentView;
	}

}
