package com.itheima.googleplay.adapter;

import java.util.List;

import com.itheima.googleplay.holder.BaseHolder;
import com.itheima.googleplay.holder.MoreHolder;
import com.itheima.googleplay.manager.ThreadManager;
import com.itheima.googleplay.tools.UiUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public abstract class DefaultAdapter<Data> extends BaseAdapter implements OnItemClickListener {
	protected List<Data> datas;
	private static final int DEFAULT_ITEM = 0;
	private static final int MORE_ITEM = 1;
	private ListView lv;
	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

	public DefaultAdapter(List<Data> datas,ListView lv) {
		this.datas = datas;
		// ��ListView������Ŀ�ĵ���¼�
		lv.setOnItemClickListener(this);
		this.lv=lv;
	}
	
	// ListView ��Ŀ����¼��ص��ķ���
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//Toast.makeText(UiUtils.getContext(), "position:"+position, 0).show();
		position=position-lv.getHeaderViewsCount();// ��ȡ��������Ŀ������   λ��ȥ������view������
		onInnerItemClick(position);
	}
	/**�ڸ÷���ȥ������Ŀ�ĵ���¼�*/
	public void onInnerItemClick(int position) {
		
	}

	@Override
	public int getCount() {
		return datas.size() + 1; // ����һ����Ŀ ���Ǽ��ظ������Ŀ
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	/** ����λ�� �жϵ�ǰ��Ŀ��ʲô���� */
	@Override
	public int getItemViewType(int position) {  //20     
		if (position == datas.size()) { // ��ǰ�����һ����Ŀ
			return MORE_ITEM;
		}
		return getInnerItemViewType(position); // ����������һ����Ŀ ����Ĭ������
	}

	protected int getInnerItemViewType(int position) {
		return DEFAULT_ITEM;
	}

	/** ��ǰListView �м��ֲ�ͬ����Ŀ���� */
	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount() + 1; // 2 �����ֲ�ͬ������
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder = null;

		switch (getItemViewType(position)) {  // �жϵ�ǰ��Ŀʱʲô����
		case MORE_ITEM:
			if(convertView==null){
				holder=getMoreHolder();
			}else{
				holder=(BaseHolder) convertView.getTag();
			}
			break;
		default:
			if (convertView == null) {
				holder = getHolder();
			} else {
				System.out.println("aaa");
				holder = (BaseHolder) convertView.getTag();
			}
			if (position < datas.size()) {
				holder.setData(datas.get(position));
			}
			break;
		}
		return holder.getContentView();  //  �����ǰHolder ǡ����MoreHolder  ֤��MoreHOlder�Ѿ���ʾ
	}
	private MoreHolder holder;
	private BaseHolder getMoreHolder() {
		if(holder!=null){
			return holder;
		}else{
			holder=new MoreHolder(this,hasMore());
			return holder;
		}
	}
	/**
	 * �Ƿ��ж��������
	 * @return
	 */
	protected boolean hasMore() {
		return true;
	}

	protected abstract BaseHolder<Data> getHolder();
	
	/**
	 * �����ظ�����Ŀ��ʾ��ʱ�� ���ø÷���
	 */
	public void loadMore() {
		ThreadManager.getInstance().createLongPool().execute(new Runnable() {
			
			@Override
			public void run() {
				// �����߳��м��ظ��� 
				final List<Data> newData = onload();
				UiUtils.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						if(newData==null){
							holder.setData(MoreHolder.LOAD_ERROR);//  
						}else if(newData.size()==0){
							holder.setData(MoreHolder.HAS_NO_MORE);
						}else{
							// �ɹ���
							holder.setData(MoreHolder.HAS_MORE);
							datas.addAll(newData);//  ��listView֮ǰ�ļ�������һ���µļ���
							notifyDataSetChanged();// ˢ�½���
						
						}
						
					}
				});
			
				
			}
		});
	}
	/**
	 * ���ظ�������
	 */
	protected  abstract List<Data> onload();

}