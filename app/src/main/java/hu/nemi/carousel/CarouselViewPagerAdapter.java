package hu.nemi.carousel;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class CarouselViewPagerAdapter extends PagerAdapter  {
    private DataSetObserver adapterObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            CarouselViewPagerAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            CarouselViewPagerAdapter.this.notifyDataSetChanged();
        }
    };

    private CarouselAdapter itemAdapter;

    public void setAdapter(CarouselAdapter itemAdapter) {
        if(this.itemAdapter != null) {
            this.itemAdapter.unregisterDataSetObserver(adapterObserver);
        }
        this.itemAdapter = itemAdapter;

        if(this.itemAdapter != null) {
            this.itemAdapter.registerDataSetObserver(adapterObserver);

        }
    }

    public CarouselAdapter getAdapter() {
        return itemAdapter;
    }

    private int mapToAdapterPosition(int position) {
        if(itemAdapter.getCount() <= 2) {
            return position;
        }

        return (position + itemAdapter.getCount() - 1) % itemAdapter.getCount();
    }

    @Override
    public int getCount() {
        if(itemAdapter.getCount() <= 2) {
            return itemAdapter.getCount();
        }

        return itemAdapter.getCount() * 2 + 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = itemAdapter.onCreateView(container, mapToAdapterPosition(position));
        container.addView(itemView);
        CarouselPageDescriptor d = new CarouselPageDescriptor(itemAdapter.getCount(), itemView);
        itemView.setTag(R.id.carousel_page_descriptor, d);
        return d;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        CarouselPageDescriptor d = (CarouselPageDescriptor) object;
        container.removeView(d.getItemView());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view.getTag(R.id.carousel_page_descriptor);
    }

    @Override
    public int getItemPosition(Object object) {
        CarouselPageDescriptor d = (CarouselPageDescriptor) object;
        if(d.getItemCount() == itemAdapter.getCount()) {
            return PagerAdapter.POSITION_UNCHANGED;
        }
        return PagerAdapter.POSITION_NONE;
    }
}
