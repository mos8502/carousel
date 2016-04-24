package hu.nemi.carouseldemo;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hu.nemi.carousel.R;

/**
 * Created by nemi on 24/04/16.
 */
public class ItemViewHolder {
    private View itemView;
    private Picasso picasso;
    private TextView title;
    private ImageView image;

    public ItemViewHolder(View itemView) {
        this.itemView = itemView;
        this.picasso = Picasso.with(itemView.getContext());
        this.title = (TextView) itemView.findViewById(R.id.carousel_title);
        this.image = (ImageView) itemView.findViewById(R.id.carousel_image);
    }

    public View getItemView() {
        return itemView;
    }

    public void setTitle(CharSequence title) {
        this.title.setText(title);
    }

    public void setImage(@DrawableRes int res) {
        picasso.load(res).into(image);
    }
}
