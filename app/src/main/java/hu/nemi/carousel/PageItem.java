package hu.nemi.carousel;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class PageItem {
    private final int imageResource;
    private final String title;

    public PageItem(@DrawableRes int imageResource, @NonNull String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    @DrawableRes public int getImageResource() {
        return imageResource;
    }

    @NonNull public String getTitle() {
        return title;
    }
}
