package com.eoe.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity implements OnPageChangeListener {
	private ViewPager mPager;
	private MyAdapter adapter;
	private LinearLayout mPointContainer;
	private TextView mTvTitle;
	private List<ImageView> mListDatas;
	int[] imgs = { R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
			R.drawable.icon4, R.drawable.icon5 };
	String[] titles = { "桃之夭夭", "灼灼其华", "他山之石", "古帝命武汤", "正域彼四方" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new MyAdapter();

		// 获取ViewPager控件
		mPager = (ViewPager) findViewById(R.id.pager);

		mPointContainer = (LinearLayout) findViewById(R.id.point_container);
		mTvTitle = (TextView) findViewById(R.id.tv_title);

		/* 初始化数据 */
		mListDatas = new ArrayList<ImageView>();
		for (int i = 0; i < imgs.length; i++) {
			// 给集合添加ImageView
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ScaleType.FIT_XY);

			mListDatas.add(iv);

			/* 添加点 */
			View point = new View(this);
			point.setBackgroundResource(R.drawable.point_normal);
			LayoutParams params = new LayoutParams(10, 10);
			if (i != 0) {
				params.leftMargin = 10;// 设置点之间的举例
			} else {
				point.setBackgroundResource(R.drawable.point_selected);

				mTvTitle.setText(titles[i]);// 设置对应标题
			}

			mPointContainer.addView(point, params);

		}

		mPager.setAdapter(adapter);// 也可以直接写成 mPager.setAdapter(new
									// MyAdapter());

		/* 实现页面滑动，点跟着变化（设置监听器） */
		mPager.setOnPageChangeListener(this);

		/* 设置默认选中中间的item */
		int middle = Integer.MAX_VALUE / 2;
		int extra = middle % mListDatas.size();
		int item = middle - extra;
		mPager.setCurrentItem(item);

	}

	private class MyAdapter extends PagerAdapter {

		private ImageView iv;

		@Override
		// 返回的是页面的数量
		public int getCount() {
			if (mListDatas != null) {

				// return mListDatas.size();
				return Integer.MAX_VALUE;
			}

			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;// 官方推荐这样写
		}

		@Override
		// 初始化item条目
		public Object instantiateItem(ViewGroup container, int position) {

			position = position % mListDatas.size();

			iv = mListDatas.get(position);// 要加载的位置
			mPager.addView(iv);// 添加要显示的view

			return iv;
		}

		@Override
		// 销毁item条目
		public void destroyItem(ViewGroup container, int position, Object object) {

			// 销毁移除item
			// object:标记

			position = position % mListDatas.size();
			iv = mListDatas.get(position);// 要移除的位置
			mPager.removeView(iv);// 移除显示的view
		}

	}

	/* 下面这些方法实现点随着页面滑动而变化 */

	// 回调方法,当viewpager的滑动状态改变时的回调
	// * @see ViewPager#SCROLL_STATE_IDLE : 闲置状态
	// * @see ViewPager#SCROLL_STATE_DRAGGING :拖动状态
	// * @see ViewPager#SCROLL_STATE_SETTLING: 固定状态
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	@Override
	// 回调方法,当viewpager滚动时的回调
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// position:当前选中的位置
		// positionOffset:滑动的百分比
		// positionOffsetPixels：偏移的距离，滑动的像素

	}

	@Override
	// 回调方法,当viewpager的某个页面选中时的回调
	public void onPageSelected(int position) {

		position = position % mListDatas.size();

		/* 设置选中点的样式 */
		int count = mPointContainer.getChildCount();
		for (int j = 0; j < count; j++) {
			View view = mPointContainer.getChildAt(j);
			view.setBackgroundResource(position == j ? R.drawable.point_selected
					: R.drawable.point_normal);

		}
		mTvTitle.setText(titles[position]);// 设置对应标题

	}
}
