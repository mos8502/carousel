package hu.nemi.carouseldemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.nemi.carousel.CarouselAdapter;

public class Adapter extends CarouselAdapter {

    private List<PageItem> items;
    private int carouselItemViewResource;

    public Adapter(@LayoutRes int carouselItemViewResource) {
        this.carouselItemViewResource = carouselItemViewResource;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View onCreateView(ViewGroup container, int adapterPosition) {
        PageItem carouselItem = items.get(adapterPosition);
        ItemViewHolder itemViewHolder = new ItemViewHolder(inflateItemView(container));
        bindCarouselItemToVIew(itemViewHolder, carouselItem);
        return itemViewHolder.getItemView();
    }

    private void bindCarouselItemToVIew(ItemViewHolder itemViewHolder, PageItem item) {
        itemViewHolder.setTitle(item.getTitle());
        itemViewHolder.setImage(item.getImageResource());
    }

    private View inflateItemView(ViewGroup container) {
        LayoutInflater layoutInflater = getLayoutInflater(container.getContext());
        return layoutInflater.inflate(carouselItemViewResource, container, false);
    }

    private LayoutInflater getLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }
}
