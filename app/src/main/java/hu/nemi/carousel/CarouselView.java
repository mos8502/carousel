package hu.nemi.carousel;

import android.content.Context;
import android.content.res.TypedArray;
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

    private CarouselViewPagerAdapter carouselViewPagerAdapter = new CarouselViewPagerAdapter();

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
        ta.recycle();
        LayoutParams layoutParams = new LayoutParams(carouselItemSize, carouselItemSize);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        addView(viewPager, layoutParams);
        viewPager.setPageMargin(carouselItemMargin);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float horizontalSizeRatio = getHorizontalSizeRatio();
        float verticalSizeRatio = getVerticalSizeRatio();

        event.setLocation(event.getX()*horizontalSizeRatio, event.getY()*verticalSizeRatio);

        return viewPager.onTouchEvent(event);
    }

    private float getHorizontalSizeRatio() {
        return getRatio(getWidth(), viewPager.getWidth());
    }

    private float getVerticalSizeRatio() {
        return getRatio(getHeight(), viewPager.getHeight());
    }

    private float getRatio(int size, int pagerSize) {
        return pagerSize / (float)size;
    }

    public void setAdapter(CarouselAdapter adapter) {
        carouselViewPagerAdapter.setAdapter(adapter);
        if(adapter != null) {
            viewPager.setAdapter(carouselViewPagerAdapter);
        } else {
            viewPager.setAdapter(null);
        }
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        if(carouselViewPagerAdapter.getAdapter().getCount() <= 2) {
            viewPager.setCurrentItem(item, smoothScroll);
        } else {
            if (item == 0) {
                item = 1;
            } else if (item == carouselViewPagerAdapter.getAdapter().getCount() - 1) {
                item = carouselViewPagerAdapter.getCount() - 2;
            } else {
                item += 1;
            }

            setCurrentCarouselPosition(item, smoothScroll);
        }
    }

    private void setCurrentCarouselPosition(int position, boolean smoothScroll) {

        if(position <= 1) {
            position += carouselViewPagerAdapter.getAdapter().getCount();
        } else if(position >= carouselViewPagerAdapter.getCount() -2) {
            position -= carouselViewPagerAdapter.getAdapter().getCount();
        }

        int currentCarouselPosition = viewPager.getCurrentItem();
        if(currentCarouselPosition != position) {
            viewPager.setCurrentItem(position, smoothScroll);
        }
    }

    public CarouselAdapter getAdapter() {
        return carouselViewPagerAdapter.getAdapter();
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
            if(carouselViewPagerAdapter.getAdapter().getCount() > 2) {
                setCurrentCarouselPosition(viewPager.getCurrentItem(), false);
            }
        }
    }
}
