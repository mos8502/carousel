package hu.nemi.carousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by nemi on 23/04/16.
 */
public class CarouselView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private PointF center = new PointF();
    private PointF initialTouch = new PointF();

    private CarouselAdapter carouselAdapter = new CarouselAdapter();

    public CarouselView(Context context) {
        this(context, null, 0);
    }

    public CarouselView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClipChildren(false);
        viewPager = new ViewPager(context);
        viewPager.setOffscreenPageLimit(6);
        viewPager.addOnPageChangeListener(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CarouselView);
        int carouselItemSize = ta.getDimensionPixelSize(R.styleable.CarouselView_carouselItemSize, MATCH_PARENT);
        int carouselItemMargin = ta.getDimensionPixelSize(R.styleable.CarouselView_carouselItemMargin, 0);
        LayoutParams layoutParams = new LayoutParams(carouselItemSize, carouselItemSize);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        addView(viewPager, layoutParams);
        viewPager.setPageMargin(carouselItemMargin);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.set(w / 2.0f, h / 2.0f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN : {
                initialTouch.set(event.getX(), event.getY());
            }
            default: {
                event.offsetLocation(center.x - initialTouch.x, center.y - initialTouch.y);
            }
        }

        return viewPager.onTouchEvent(event);
    }

    public void setAdapter(PagerAdapter adapter) {
        carouselAdapter.setAdapter(adapter);
        if(adapter != null) {
            viewPager.setAdapter(carouselAdapter);
        } else {
            viewPager.setAdapter(null);
        }
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        if(carouselAdapter.getAdapter().getCount() <= 2) {
            viewPager.setCurrentItem(item, smoothScroll);
        } else {
            if (item == 0) {
                item = 1;
            } else if (item == carouselAdapter.getAdapter().getCount() - 1) {
                item = carouselAdapter.getCount() - 2;
            } else {
                item += 1;
            }

            setCurrentCarouselPosition(item, smoothScroll);
        }
    }

    private void setCurrentCarouselPosition(int position, boolean smoothScroll) {

        if(position == 1) {
            position += carouselAdapter.getAdapter().getCount();
        } else if(position == carouselAdapter.getCount() -2) {
            position -= carouselAdapter.getAdapter().getCount();
        }

        int currentCarouselPosition = viewPager.getCurrentItem();
        if(currentCarouselPosition != position) {
            viewPager.setCurrentItem(position, smoothScroll);
        }
    }

    public PagerAdapter getAdapter() {
        return carouselAdapter.getAdapter();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state == ViewPager.SCROLL_STATE_IDLE) {
            setCurrentCarouselPosition(viewPager.getCurrentItem(), false);
        }
    }
}