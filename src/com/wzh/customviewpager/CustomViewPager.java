package com.wzh.customviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class CustomViewPager extends ViewGroup {

	private GestureDetector detector;
	private Scroller scroller;
	private onPageChangeListener mOnPageChangeListener;
	private int startX;
	private int startY;

	public CustomViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomViewPager(Context context) {
		this(context, null);
	}

	// 初始化方法
	private void init() {
		detector = new GestureDetector(getContext(),
				new GestureDetector.SimpleOnGestureListener() {
					// 手指进行一个移动操作，该方法就会被调用

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						/**
						 * * e1 :一个移动操作的按下动作，按下事件action_down
						 */
						scrollBy((int) distanceX, 0);
						return super.onScroll(e1, e2, distanceX, distanceY);
					}
				});

		scroller = new Scroller(getContext());
	}

	// 排版,布局方法
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < this.getChildCount(); i++) {
			View childView = this.getChildAt(i);
			childView.layout(i * getWidth(), 0, (i + 1) * getWidth(),
					getHeight());
		}

	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//遍历所有的子控件，调用子控件的测量，对子控件的儿子们进行测量
		for (int i = 0; i < this.getChildCount(); i++) {
			View childView = this.getChildAt(i);
			childView.measure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			//System.out.println("scrollX:" + scrollX);
			int position=(scrollX+getWidth()/2)/getWidth();
			//scrollTo(position*getWidth(), 0);
			
			//设置右边界
			if(position>this.getChildCount()-1){
				position=this.getChildCount()-1;
			}
			//使用scroller实现界面的缓慢滑动
			setCurrentItem(position);
			break;

		default:
			break;
		}
		return true;
	}

	//事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			detector.onTouchEvent(ev);
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int endX=(int) ev.getX();
			int endY=(int) ev.getY();
			
			//计算出间距,判断是水平还是垂直移动
			int diffX=endX-startX;
			int diffY=endY-startY;
			if(Math.abs(diffX)>Math.abs(diffY)){
				return true;
			}else{
				return false;
			}

		default:
			break;
		}
		return false;
	}
	
	public void setCurrentItem(int position) {
		int startX=getScrollX();
		int dx=position*getWidth()-startX;
		int duration=Math.abs(dx)*10;
		scroller.startScroll(startX, 0, dx, 0, duration);
		invalidate();
		
		if(mOnPageChangeListener!=null){
			mOnPageChangeListener.onPageChange(position);
		}
	}
	
	@Override
	public void computeScroll() {
		if(scroller.computeScrollOffset()){
			int currX = scroller.getCurrX();
			//System.out.println("currX:" + currX);
			scrollTo(currX, 0);
			invalidate();
		}
	}
	
	public interface onPageChangeListener{
		public void onPageChange(int position);
	}
	
	public void setOnPageChangeListener(onPageChangeListener listener){
		this.mOnPageChangeListener=listener;
	}
}
