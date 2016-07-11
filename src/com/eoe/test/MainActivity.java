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
	String[] titles = { "��֮زز", "�����仪", "��ɽ֮ʯ", "�ŵ�������", "������ķ�" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new MyAdapter();

		// ��ȡViewPager�ؼ�
		mPager = (ViewPager) findViewById(R.id.pager);

		mPointContainer = (LinearLayout) findViewById(R.id.point_container);
		mTvTitle = (TextView) findViewById(R.id.tv_title);

		/* ��ʼ������ */
		mListDatas = new ArrayList<ImageView>();
		for (int i = 0; i < imgs.length; i++) {
			// ���������ImageView
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ScaleType.FIT_XY);

			mListDatas.add(iv);

			/* ��ӵ� */
			View point = new View(this);
			point.setBackgroundResource(R.drawable.point_normal);
			LayoutParams params = new LayoutParams(10, 10);
			if (i != 0) {
				params.leftMargin = 10;// ���õ�֮��ľ���
			} else {
				point.setBackgroundResource(R.drawable.point_selected);

				mTvTitle.setText(titles[i]);// ���ö�Ӧ����
			}

			mPointContainer.addView(point, params);

		}

		mPager.setAdapter(adapter);// Ҳ����ֱ��д�� mPager.setAdapter(new
									// MyAdapter());

		/* ʵ��ҳ�滬��������ű仯�����ü������� */
		mPager.setOnPageChangeListener(this);

		/* ����Ĭ��ѡ���м��item */
		int middle = Integer.MAX_VALUE / 2;
		int extra = middle % mListDatas.size();
		int item = middle - extra;
		mPager.setCurrentItem(item);

	}

	private class MyAdapter extends PagerAdapter {

		private ImageView iv;

		@Override
		// ���ص���ҳ�������
		public int getCount() {
			if (mListDatas != null) {

				// return mListDatas.size();
				return Integer.MAX_VALUE;
			}

			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;// �ٷ��Ƽ�����д
		}

		@Override
		// ��ʼ��item��Ŀ
		public Object instantiateItem(ViewGroup container, int position) {

			position = position % mListDatas.size();

			iv = mListDatas.get(position);// Ҫ���ص�λ��
			mPager.addView(iv);// ���Ҫ��ʾ��view

			return iv;
		}

		@Override
		// ����item��Ŀ
		public void destroyItem(ViewGroup container, int position, Object object) {

			// �����Ƴ�item
			// object:���

			position = position % mListDatas.size();
			iv = mListDatas.get(position);// Ҫ�Ƴ���λ��
			mPager.removeView(iv);// �Ƴ���ʾ��view
		}

	}

	/* ������Щ����ʵ�ֵ�����ҳ�滬�����仯 */

	// �ص�����,��viewpager�Ļ���״̬�ı�ʱ�Ļص�
	// * @see ViewPager#SCROLL_STATE_IDLE : ����״̬
	// * @see ViewPager#SCROLL_STATE_DRAGGING :�϶�״̬
	// * @see ViewPager#SCROLL_STATE_SETTLING: �̶�״̬
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	@Override
	// �ص�����,��viewpager����ʱ�Ļص�
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// position:��ǰѡ�е�λ��
		// positionOffset:�����İٷֱ�
		// positionOffsetPixels��ƫ�Ƶľ��룬����������

	}

	@Override
	// �ص�����,��viewpager��ĳ��ҳ��ѡ��ʱ�Ļص�
	public void onPageSelected(int position) {

		position = position % mListDatas.size();

		/* ����ѡ�е����ʽ */
		int count = mPointContainer.getChildCount();
		for (int j = 0; j < count; j++) {
			View view = mPointContainer.getChildAt(j);
			view.setBackgroundResource(position == j ? R.drawable.point_selected
					: R.drawable.point_normal);

		}
		mTvTitle.setText(titles[position]);// ���ö�Ӧ����

	}
}
