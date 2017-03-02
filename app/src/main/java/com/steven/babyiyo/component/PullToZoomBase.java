package com.steven.babyiyo.component;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.steven.babyiyo.R;

/**
 * ���������������
 * @author zhangshuo
 * @param <T>
 */
public abstract class PullToZoomBase<T extends View> extends LinearLayout{

	/**����ϵ��*/
	private static final float FRICTION = 2.0f;
    protected T mRootView;
    protected View mHeaderView;//ͷ��View
    protected View mZoomView;//��������View
	
	protected int mScreenHeight;
    protected int mScreenWidth;

    private boolean isZoomEnabled = true;
    private boolean isParallax = true;
    private boolean isZooming = false;
    private boolean isHideHeader = false;


        private int mTouchSlop;
        private boolean mIsBeingDragged = false;
        private float mLastMotionY;
        private float mLastMotionX;
        private float mInitialMotionY;
        private float mInitialMotionX;

        protected OnPullZoomListener onPullZoomListener;

        public PullToZoomBase(Context context) {
            this(context, null);
        }

        public PullToZoomBase(Context context, AttributeSet attrs) {
            super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
        setGravity(Gravity.CENTER);

        ViewConfiguration config = ViewConfiguration.get(context);
        mTouchSlop = config.getScaledTouchSlop();

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        mScreenHeight = localDisplayMetrics.heightPixels;
        mScreenWidth = localDisplayMetrics.widthPixels;

        // Refreshable View
        // By passing the attrs, we can add ListView/GridView params via XML
        mRootView = createRootView(context, attrs);

        if (attrs != null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
            //��ʼ��״̬View
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PullToZoomView);

            int zoomViewResId = a.getResourceId(R.styleable.PullToZoomView_zoomView, 0);
            if (zoomViewResId > 0) {
                mZoomView = mLayoutInflater.inflate(zoomViewResId, null, false);
            }

            int headerViewResId = a.getResourceId(R.styleable.PullToZoomView_headerView, 0);
            if (headerViewResId > 0) {
                mHeaderView = mLayoutInflater.inflate(headerViewResId, null, false);
            }

            isParallax = a.getBoolean(R.styleable.PullToZoomView_isHeaderParallax, true);

            // Let the derivative classes have a go at handling attributes, then
            // recycle them...
            handleStyledAttributes(a);
            a.recycle();
        }
        addView(mRootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
	
	public void setOnPullZoomListener(OnPullZoomListener onPullZoomListener) {
        this.onPullZoomListener = onPullZoomListener;
    }

    public T getPullRootView() {
        return mRootView;
    }

    public View getZoomView() {
        return mZoomView;
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public boolean isPullToZoomEnabled() {
        return isZoomEnabled;
    }

    public boolean isZooming() {
        return isZooming;
    }

    public boolean isParallax() {
        return isParallax;
    }

    public boolean isHideHeader() {
        return isHideHeader;
    }

    public void setZoomEnabled(boolean isZoomEnabled) {
        this.isZoomEnabled = isZoomEnabled;
    }

    public void setParallax(boolean isParallax) {
        this.isParallax = isParallax;
    }

    public void setHideHeader(boolean isHideHeader) {//header��ʾ����Zoom
        this.isHideHeader = isHideHeader;
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!isPullToZoomEnabled() || isHideHeader()) {
            return false;
        }

        final int action = event.getAction();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsBeingDragged = false;
            return false;
        }

        if (action != MotionEvent.ACTION_DOWN && mIsBeingDragged) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_MOVE: {
                if (isReadyForPullDown()) {
                    final float y = event.getY(), x = event.getX();
                    final float diff, oppositeDiff, absDiff;

                    // We need to use the correct values, based on scroll
                    // direction
                    diff = y - mLastMotionY;
                    oppositeDiff = x - mLastMotionX;
                    absDiff = Math.abs(diff);

                    if (absDiff > mTouchSlop && absDiff > Math.abs(oppositeDiff)) {
                        if (diff >= 1f && isReadyForPullDown()) {
                            mLastMotionY = y;
                            mLastMotionX = x;
                            mIsBeingDragged = true;
                        }
                    }
                }
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                if (isReadyForPullDown()) {
                    mLastMotionY = mInitialMotionY = event.getY();
                    mLastMotionX = mInitialMotionX = event.getX();
                    mIsBeingDragged = false;
                }
                break;
            }
        }

        return mIsBeingDragged;
    }
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isPullToZoomEnabled() || isHideHeader()) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                if (mIsBeingDragged) {
                    mLastMotionY = event.getY();
                    mLastMotionX = event.getX();
                    pullEvent();
                    isZooming = true;
                    return true;
                }
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                if (isReadyForPullDown()) {
                    mLastMotionY = mInitialMotionY = event.getY();
                    mLastMotionX = mInitialMotionX = event.getX();
                    return true;
                }
                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                if (mIsBeingDragged) {
                    mIsBeingDragged = false;
                    // If we're already refreshing, just scroll back to the top
                    if (isZooming()) {
                        smoothScrollToTop();
                        if (onPullZoomListener != null) {
                            onPullZoomListener.onPullZoomEnd();
                        }
                        onZoomRelease(mInitialMotionY, mLastMotionY);
                        isZooming = false;
                        return true;
                    }
                    return true;
                }
                
                break;
            }
        }
        return false;
    }

    private void pullEvent() {
        final int newScrollValue;
        final float initialMotionValue, lastMotionValue;

        initialMotionValue = mInitialMotionY;
        lastMotionValue = mLastMotionY;

        newScrollValue = Math.round(Math.min(initialMotionValue - lastMotionValue, 0) / FRICTION);

        pullHeaderToZoom(newScrollValue);
        if (onPullZoomListener != null) {
            onPullZoomListener.onPullZooming(newScrollValue);
        }
    }

    public abstract void handleStyledAttributes(TypedArray a);
    
    protected abstract void pullHeaderToZoom(int newScrollValue);

    public abstract void setHeaderView(View headerView);

    public abstract void setZoomView(View zoomView);

    protected abstract T createRootView(Context context, AttributeSet attrs);

    protected abstract void smoothScrollToTop();

    protected abstract boolean isReadyForPullDown();
    
    protected abstract boolean isReadyForPullUp();
    
    /**
     * �ͷ�����
     * @param initMotionY ���ſ�ʼʱ��Yλ��
     * @param lastMotionY �����ͷ�ʱ��Yλ��
     */
    protected abstract void onZoomRelease(float initMotionY, float lastMotionY);
    
    public interface OnPullZoomListener {
    	
        public void onPullZooming(int newScrollValue);

        public void onPullZoomEnd();
        
        public void onRefresh();
        
        public void onLoad();
    }
}
