package com.yyyu.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yyyu.baselibrary.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 说明：自定义轮播条
 *
 * @author yyyu
 * @date 2016/2/27
 */
public class ViewpagerLooper extends LinearLayout {

    private final static String TAG = "ViewpagerLooper-----";

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 指示器的颜色
     */
    private int indicator_normal_color;
    private int indicator_selected_color;

    /**
     * 默认背景
     */
    private final static int default_bg = 0x00000000;


    /**
     * 关联的ViewPager
     */
    private ViewPager mViewPager;

    /**
     * 选中的点的偏移
     */
    private float dot_translate_x;

    /**
     * 选中点的起始位置
     */
    private float dot_init_translate_x;

    /**
     * 圆点的大小
     */
    private float DOT_SIZE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 3, getResources().getDisplayMetrics());

    /**
     * 圆点View的大小
     */
    private float DOT_VIEW_SIZE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());

    private int dataSize;

    /**
     * 页面切换间隔时间
     */
    private final long spaceTime = 8 * 1000;


    public ViewpagerLooper(Context context) {
        this(context, null);
    }

    public ViewpagerLooper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewpagerLooper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //得到自定义属性*
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewPageLooper);
        indicator_normal_color = ta.getColor(R.styleable.ViewPageLooper_dot_normal_color, 0xffffffff);
        indicator_selected_color = ta.getColor(R.styleable.ViewPageLooper_dot_selected_color, 0xff0092a4);
        ta.recycle();
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        //设置默认背景
        setBackgroundColor(default_bg);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthGet = MeasureSpec.getSize(widthMeasureSpec); // 得到的测量的宽
        int heightGet = MeasureSpec.getSize(heightMeasureSpec);// 得到的测量的高
        int tempWidth = 0;
        int tempHeight = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            tempWidth += DOT_VIEW_SIZE;
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthGet : tempWidth,
                heightMode == MeasureSpec.EXACTLY ? heightGet : tempHeight);

    }

    /**
     * 这个方法是用来绘制子控件的
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
         /*画出指示器*/
        mPaint.setColor(indicator_selected_color);
        canvas.translate(dot_init_translate_x + dot_translate_x, getHeight() / 2);
        canvas.drawCircle(0, 0, DOT_SIZE, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (dataSize > 0) dot_init_translate_x = getWidth() / 2 / dataSize;

    }

    /**
     * 添加指示圆点
     *
     * @param dataSize
     */
    public void addIndicator(int dataSize) {
        if (getChildCount() == dataSize) {//--防止重复添加
            return;
        }
        this.dataSize = dataSize;
        mHandler.sendEmptyMessageDelayed(0, spaceTime);
        for (int i = 0; i < dataSize; i++) {
            View view = generateCircleDot(getContext());
            addView(view);
        }
    }

    /**
     * 生成小圆点
     */
    private View generateCircleDot(Context ctx) {
        View view = new View(ctx) {
            @Override
            protected void onDraw(Canvas canvas) {
                mPaint.setColor(indicator_normal_color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, DOT_SIZE, mPaint);
            }
        };
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = (int) DOT_VIEW_SIZE;
        lp.gravity = Gravity.CENTER;
        view.setBackgroundColor(0x00000000);
        view.setLayoutParams(lp);
        return view;
    }

    /**
     * 关联ViewPager
     */
    public void setViewPager(ViewPager viewPager, final LooperPagerAdapter adapter) {
        this.mViewPager = viewPager;
        viewPager.setAdapter(adapter);
        //---默认显示第二个元素（也就是原来数据集合中的第一个元素）
        if (adapter.mData.size() > 1) {
            mViewPager.setCurrentItem(1, false);
        } else {
            mViewPager.setCurrentItem(0, false);
        }
        addIndicator(adapter.oData.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (adapter.mData.size() > 1) {
                    dot_translate_x = getWidth() / dataSize * (positionOffset + position - 1);//---减去开头被隐藏的1个元素
                } else {
                    dot_translate_x = getWidth() / dataSize * (positionOffset + position);
                }
                invalidate();
            }

            @Override
            public void onPageSelected(int position) {

                if (adapter.mData.size() > 1) {
                    if (position == adapter.mData.size() - 1) {//---滑到最后一个时
                        mViewPager.setCurrentItem(1, false);
                        return;
                    } else if (position == 0) { //---滑到第一个时
                        mViewPager.setCurrentItem(adapter.mData.size() - 2, false);
                        return;
                    }
                    if (mOnPagerChangedListener != null) {
                        mOnPagerChangedListener.onPagerChanged(position - 1);
                    }
                } else {
                    if (mOnPagerChangedListener != null) {
                        mOnPagerChangedListener.onPagerChanged(position);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int index = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++index);
            mHandler.sendEmptyMessageDelayed(0, spaceTime);
        }
    };

    public static abstract class LooperPagerAdapter<T> extends PagerAdapter {

        private Context mContext;
        private List<T> oData; // --- 原始的数据
        private List<T> mData;
        private int containerId;

        public LooperPagerAdapter(Context context, List<T> data, int containerId) {
            this.mContext = context;
            this.containerId = containerId;
            this.oData = data;
            mData = new ArrayList<>();
            if (data.size() > 1) {
                T firstItem = data.get(data.size() - 1);
                T lastItem = data.get(0);
                mData.add(firstItem);
                for (T bean : data) {
                    mData.add(bean);
                }
                mData.add(lastItem);
            } else {
                mData = data;
            }
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final View viewItem = LayoutInflater.from(mContext).inflate(containerId, container, false);
            setView(viewItem, mData.get(position));
            container.addView(viewItem);
            return viewItem;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        protected abstract void setView(View viewItem, T dataBean);


    }

    private OnPagerChangedListener mOnPagerChangedListener;

    public void setOnPagerChangedListener(OnPagerChangedListener onPagerChangeeListener) {
        this.mOnPagerChangedListener = onPagerChangeeListener;
    }

    public interface OnPagerChangedListener {
        void onPagerChanged(int position);
    }

}













