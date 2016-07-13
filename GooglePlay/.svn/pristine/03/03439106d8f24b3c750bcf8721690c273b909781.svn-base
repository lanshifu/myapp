package com.itheima.googleplay.fragment;

import java.util.List;
import java.util.Random;

import com.itheima.googleplay.R;
import com.itheima.googleplay.protocol.TopProtocol;
import com.itheima.googleplay.tools.DrawableUtils;
import com.itheima.googleplay.tools.UiUtils;
import com.itheima.googleplay.view.Flowlayout;
import com.itheima.googleplay.view.LoadingPage.LoadResult;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class TopFragment extends BaseFragment {

	private List<String> datas;

	@Override
	public View createSuccessView() {
		ScrollView scrollView=new ScrollView(UiUtils.getContext());
		scrollView.setBackgroundResource(R.drawable.grid_item_bg_normal);
		Flowlayout layout=new Flowlayout(UiUtils.getContext());
		int padding=UiUtils.dip2px(13);
		layout.setPadding(padding, padding, padding, padding);
		//layout.setOrientation(LinearLayout.VERTICAL);// 设置线性布局的方向
		int backColor = 0xffcecece;
		Drawable pressedDrawable=DrawableUtils.createShape(backColor);// 按下显示的图片
		for(int i=0;i<datas.size();i++){
			TextView textView=new TextView(UiUtils.getContext());
			final String str=datas.get(i);
			textView.setText(str);
		
			Random random=new Random();   //创建随机
			int red = random.nextInt(200)+22;    
			int green = random.nextInt(200)+22;  
			int blue = random.nextInt(200)+22;     
			int color=Color.rgb(red, green, blue);//范围 0-255 
			GradientDrawable createShape = DrawableUtils.createShape(color); // 默认显示的图片
			StateListDrawable createSelectorDrawable = DrawableUtils.createSelectorDrawable(pressedDrawable, createShape);// 创建状态选择器
			textView.setBackgroundDrawable(createSelectorDrawable);
			textView.setTextColor(Color.WHITE);
			//textView.setTextSize(UiUtils.dip2px(14));
			int textPaddingV = UiUtils.dip2px(4);
			int textPaddingH = UiUtils.dip2px(7);
			textView.setPadding(textPaddingH, textPaddingV, textPaddingH, textPaddingV); //设置padding 
			textView.setClickable(true);//设置textView可以被点击
			textView.setOnClickListener(new OnClickListener() {  // 设置点击事件
				
				@Override
				public void onClick(View v) {
					Toast.makeText(UiUtils.getContext(), str, 0).show();
				}
			});
			layout.addView(textView,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, -2));// -2 包裹内容
		}
		
		scrollView.addView(layout);
		return scrollView;
	}

	@Override
	protected LoadResult load() {
		TopProtocol protocol=new TopProtocol();
		datas = protocol.load(0);
		return checkData(datas);
	}
}
