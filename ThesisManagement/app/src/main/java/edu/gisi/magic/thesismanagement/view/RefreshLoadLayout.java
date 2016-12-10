package edu.gisi.magic.thesismanagement.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import edu.gisi.magic.thesismanagement.R;

/**
 * 继承自SwipeRefreshLayout,从而实现滑动到底部时上拉加载更多的功能.
 */
public class RefreshLoadLayout extends SwipeRefreshLayout implements OnScrollListener {

	/**
	 * listview实例
	 */
	private ListView mListView;
	private View mListViewFooter;

	/**
	 * 上拉监听器, 到了最底部的上拉加载操作
	 */
	private OnLoadListener mOnLoadListener;

	/**
	 * 按下时的y坐标
	 */
	private int mYDown = -1;
	/**
	 * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
	 */
	private int mYLast = -1;
	/**
	 * 是否有更多
	 */
	private boolean hasMore = true;
	/**
	 * 是否在加载中 ( 上拉加载更多 )
	 */
	private boolean isLoading = false;
	/**
	 * 触发阈值
	 */
	private int mTouchSlop;

	/**
	 * @param context
	 */
	public RefreshLoadLayout(Context context) {
		this(context, null);
	}

	@SuppressLint("InflateParams")
	public RefreshLoadLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setColorSchemeResources(R.color.bg_header,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mListViewFooter = LayoutInflater.from(context).inflate(R.layout.fragment_listview_footer_loading, null, false);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		// 初始化ListView对象
		if (mListView == null) {
			for (int i=0; i<getChildCount(); i++) {
				View childView = getChildAt(i);
				if (childView instanceof ListView) {
					mListView = (ListView) childView;

					// 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
					mListView.setOnScrollListener(this);

					mListView.addFooterView(mListViewFooter);
				}
			}
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();

		switch (action) {
			case MotionEvent.ACTION_DOWN:
				// 按下
				mYDown = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				// 移动
				mYLast = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_UP:
				// 抬起
				if (canLoad()) {
					loadData();
				}
				break;
			default:
				break;
		}

		return super.dispatchTouchEvent(event);
	}

	/**
	 * 当满足如下条件时可以加载更多：
	 * 1、到了最底部
	 * 2、不在加载中状态
	 * 3、上拉操作
	 * 4、有更多数据可被加载
	 * 
	 * @return
	 */
	private boolean canLoad() {
		return isBottom() && !isLoading && isPullUp() && hasMore;
	}

	/**
	 * 判断是否到了最底部
	 */
	private boolean isBottom() {
//		if (mListView != null && mListView.getAdapter() != null) {
//			return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
//		}

		if (mListView != null && mListView.getAdapter() != null) {
			View lastVisibleChild = mListView.getChildAt(mListView.getChildCount() - 1);
			int lastVisiblePos = mListView.getLastVisiblePosition();
			if (lastVisibleChild == null) {
				return true;
			}
			return lastVisiblePos == mListView.getCount() - 1 && lastVisibleChild.getBottom() == mListView.getHeight();
		}

		return false;
	}

	/**
	 * 是否是上拉操作
	 * 
	 * @return
	 */
	private boolean isPullUp() {
		return mYDown > 0 && mYLast > 0 && (mYDown - mYLast) >= mTouchSlop;
	}

	/**
	 * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
	 */
	private void loadData() {
		if (mOnLoadListener != null) {
			// 设置状态
			setLoading(true);
			mOnLoadListener.onLoad();
		}
	}

	/**
	 * 设置为加载中状态
	 * @param loading
	 */
	public void setLoading(boolean loading) {
		isLoading = loading;
		if (isLoading) {
			/**
			 * 安卓4.4以下的系统，addFooterView必须在setAdapter之前调用
			 */
//			mListView.addFooterView(mListViewFooter);
			mListViewFooter.setVisibility(VISIBLE);
		} else {
			/**
			 * 安卓4.4以下的系统，removeFooterView必须在setAdapter之前调用
			 */
//				mListView.removeFooterView(mListViewFooter);
			mListViewFooter.setVisibility(GONE);

			mYDown = -1;
			mYLast = -1;
		}
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	/**
	 * @param loadListener
	 */
	public void setOnLoadListener(OnLoadListener loadListener) {
		mOnLoadListener = loadListener;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		//nothing to do
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// 滚动时到了最底部也可以加载更多
		if (canLoad()) {
			loadData();
		}
	}

	/**
	 * 加载更多的监听器
	 */
	public interface OnLoadListener {
		void onLoad();
	}
}