package hu.nemi.carousel;

import android.view.View;

/**
 * Created by nemi on 24/04/16.
 */
class CarouselPageDescriptor {
    private final int itemCount;
    private final View itemView;

    public CarouselPageDescriptor(int itemCount, View itemView) {
        this.itemCount = itemCount;
        this.itemView = itemView;
    }

    public int getItemCount() {
        return itemCount;
    }

    public View getItemView() {
        return itemView;
    }
}
