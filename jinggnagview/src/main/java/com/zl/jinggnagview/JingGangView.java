package com.zl.jinggnagview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class JingGangView<B, H extends RecyclerView.ViewHolder> extends FrameLayout {
    public static int ROW_HEIGTH = dpToPx(80);
    public static int SPAN_COUNT = 5;
    public static int ITEM_COUNT = 10;
    private AppCompatActivity activity;
    private ViewPager2 viewPager;

    private final List<B> data = new ArrayList<>();
    private final List<IconFragment<B, H>> list = new ArrayList<>();
    private final List<Integer> heights = new ArrayList<>();

    private boolean isStar = true;
    private int index;
    private int paddingBottom = 0;
    private int paddingTop = 0;

    public JingGangView(@NonNull AppCompatActivity context) {
        super(context);
        init(context, null);
    }

    public JingGangView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public JingGangView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        viewPager = new ViewPager2(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewPager.setLayoutParams(lp);
        viewPager.setBackgroundColor(0xffffffff);
        addView(viewPager);
    }

    public JingGangView<B, H> setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    public JingGangView<B, H> setSpanCount(int spanCount) {
        SPAN_COUNT = spanCount;
        return this;
    }

    public JingGangView<B, H> setItemCount(int itemCount) {
        ITEM_COUNT = itemCount;
        return this;
    }

    public JingGangView<B, H> setRowHeight(int value) {
        ROW_HEIGTH = dpToPx(value);
        return this;
    }

    public JingGangView<B, H> setBackGround(int res) {
        viewPager.setBackgroundResource(res);
        return this;
    }

    public JingGangView<B, H> setBackPadding(int left, int top, int right, int bottom) {
        paddingBottom = dpToPx(bottom);
        paddingTop = dpToPx(top);
        viewPager.setPadding(dpToPx(left), paddingTop, dpToPx(right), paddingBottom);
        return this;
    }

    public void setData(List<B> iconBeans, IIconAdapterListener<B, H> listener) {
        list.clear();
        data.clear();
        heights.clear();
        data.addAll(iconBeans);
        if (data.size() == 0)
            return;
        int size = (data.size() / ITEM_COUNT) + 1;
        for (int i = 0; i < size; i++) {
            // 0,9  10 19
            ArrayList<B> beans;
            if (i < size - 1) {
                beans = new ArrayList<>(data.subList(i * ITEM_COUNT, i * ITEM_COUNT + ITEM_COUNT));
                heights.add(ROW_HEIGTH * (int) Math.ceil((float) ITEM_COUNT / (float) SPAN_COUNT));
            } else {
                beans = new ArrayList<>(data.subList(i * ITEM_COUNT, data.size()));
                heights.add(beans.size() > SPAN_COUNT ? ROW_HEIGTH * (int) Math.ceil((float) ITEM_COUNT / (float) SPAN_COUNT) : ROW_HEIGTH);
            }
            IconFragment<B, H> iconFragment = new IconFragment<B, H>(listener, beans);
            list.add(iconFragment);
        }
        initPage();
    }


    private void initPage() {
        PageAdapter<B, H> adapter = new PageAdapter<>(activity, list, activity.getSupportFragmentManager(), activity.getLifecycle());
        viewPager.setAdapter(adapter);
        if (list.size() == 0) return;
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isStar) {
                    isStar = false;
                    updatePagerHeightForChild(list.get(index).requireView(), viewPager);
                }
            }
        });

        boolean isyy = true;
        int h = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (h == 0) {
                h = heights.get(i);
            } else if (h != heights.get(i)) {
                isyy = false;
                break;
            }
        }
        if (isyy || list.size() == 1) return;

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                positionOffset = (float) doubleNumber(positionOffset);
                if (positionOffset == 0) return;
                int h;
                float p;
                int pos;
                if (position >= index) {
                    h = getTargetHeigth(position, true);
                    p = positionOffset;
                    pos = position;
                } else {
                    h = getTargetHeigth(position, false);
                    p = 1 - positionOffset;
                    pos = position+1;
                }
                p = (float) doubleNumber(p);
                updatePagerHeightForChild(p,pos, h, viewPager);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == 0) {
                    index = viewPager.getCurrentItem();
                }
            }
        });
    }


    private int getTargetHeigth(int pos, boolean isLeft) {
        try {
            int target = isLeft ? (pos + 1) : pos;
            return heights.get(target);
        } catch (Exception ig) {
            return -1;
        }

//        try {
//            int target = isLeft ? (index + 1) : (index - 1);
//            return heights.get(target);
//        } catch (Exception ig) {
//            return -1;
//        }

    }

    private void updatePagerHeightForChild(View view, ViewPager2 pager) {
        int wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
        int hMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.EXACTLY);
        view.measure(wMeasureSpec, hMeasureSpec);
        if (pager.getLayoutParams().height != view.getMeasuredHeight()) {
            ViewGroup.LayoutParams lp = pager.getLayoutParams();
            lp.height = view.getMeasuredHeight() + paddingTop + paddingBottom;
            pager.setLayoutParams(lp);
        }
    }

    private void updatePagerHeightForChild(float percent, int position,int toHeight,  ViewPager2 pager) {
        if (toHeight < 0 || percent < 0) return;
        pager.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams lp = pager.getLayoutParams();
                int height = heights.get(position);
                if (height!=toHeight){
                    if (height > toHeight) {
                        lp.height = (int) (height - (height - toHeight) * percent) + paddingTop + paddingBottom;
                    } else {
                        lp.height = (int) (height + (toHeight - height) * percent) + paddingTop + paddingBottom;
                    }
                    pager.setLayoutParams(lp);
                    pager.requestLayout();
                }
            }
        });
    }

    public static int dpToPx(int dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }

    private double doubleNumber(double value) {
        BigDecimal bd = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}