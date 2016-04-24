package hu.nemi.carousel;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nemi on 24/04/16.
 */
public abstract class CarouselAdapter {
    private DataSetObservable dataSetObservable = new DataSetObservable();

    public abstract int getCount();
    public abstract View onCreateView(ViewGroup container, int adapterPosition);

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        dataSetObservable.registerObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        dataSetObservable.unregisterObserver(dataSetObserver);
    }

    public void notifyDataSetChanged() {
        dataSetObservable.notifyChanged();
    }
}
