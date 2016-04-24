package hu.nemi.carousel;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class CarouselAdapter extends PagerAdapter  {
    private DataSetObserver adapterObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            CarouselAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            CarouselAdapter.this.notifyDataSetChanged();
        }
    };

    private PagerAdapter adapter;

    public void setAdapter(PagerAdapter adapter) {
        if(this.adapter != null) {
            this.adapter.unregisterDataSetObserver(adapterObserver);
        }
        this.adapter = adapter;

        if(this.adapter != null) {
            this.adapter.registerDataSetObserver(adapterObserver);

        }
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }

    private int mapToAdapterPosition(int position) {
        if(adapter.getCount() <= 2) {
            return position;
        }

        return (position + adapter.getCount() - 1) % adapter.getCount();
    }

    @Override
    public int getCount() {
        if(adapter.getCount() <= 2) {
            return adapter.getCount();
        }

        return adapter.getCount() * 2 + 2;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        adapter.startUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return adapter.instantiateItem(container, mapToAdapterPosition(position));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        adapter.destroyItem(container, mapToAdapterPosition(position), object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        adapter.setPrimaryItem(container, mapToAdapterPosition(position), object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        adapter.finishUpdate(container);
    }

    @Override
    public void startUpdate(View container) {
        adapter.startUpdate(container);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        return adapter.instantiateItem(container, mapToAdapterPosition(position));
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        adapter.destroyItem(container, mapToAdapterPosition(position), object);
    }

    @Override
    public void setPrimaryItem(View container, int position, Object object) {
        adapter.setPrimaryItem(container, mapToAdapterPosition(position), object);
    }

    @Override
    public void finishUpdate(View container) {
        adapter.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return adapter.isViewFromObject(view, object);
    }

    @Override
    public Parcelable saveState() {
        return adapter.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        adapter.restoreState(state, loader);
    }

    @Override
    public int getItemPosition(Object object) {
        return adapter.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        adapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return adapter.getPageTitle(mapToAdapterPosition(position));
    }

    @Override
    public float getPageWidth(int position) {
        return adapter.getPageWidth(mapToAdapterPosition(position));
    }
}
