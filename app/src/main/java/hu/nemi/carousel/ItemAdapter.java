package hu.nemi.carousel;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends PagerAdapter {

    private List<PageItem> items;
    private int carouselItemViewResource;

    public ItemAdapter(@LayoutRes int carouselItemViewResource) {
        this.carouselItemViewResource = carouselItemViewResource;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PageItem carouselItem = items.get(position);
        ItemViewHolder itemViewHolder = new ItemViewHolder(inflateItemView(container));
        bindCarouselItemToVIew(itemViewHolder, carouselItem);
        container.addView(itemViewHolder.getItemView());
        return itemViewHolder;
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) object;
        container.removeView(itemViewHolder.getItemView());
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) object;
        return itemViewHolder.getItemView() == view;
    }
}
