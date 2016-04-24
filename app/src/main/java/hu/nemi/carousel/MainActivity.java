package hu.nemi.carousel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int CAROUSEL_ITEM_COUNT = 3;
    static final int[] CAROUSEL_IMAGE_RES = {
            R.drawable.carousel_image_1,
            R.drawable.carousel_image_2,
            R.drawable.carousel_image_3,
            R.drawable.carousel_image_4
    };

    CarouselView carouselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = (CarouselView) findViewById(R.id.carousel_container);
        ItemAdapter itemAdapter = new ItemAdapter(R.layout.item_view);
        itemAdapter.setItems(fetchCarouselItems());
        carouselView.setAdapter(itemAdapter);
        carouselView.setCurrentItem(0, false);
    }

    List<PageItem> fetchCarouselItems() {
        List<PageItem> carouselItemList = new ArrayList<>();
        for(int i = 0; i < CAROUSEL_ITEM_COUNT; ++i) {
            carouselItemList.add(getCarouselItemForPosition(i));
        }
        return carouselItemList;
    }

    PageItem getCarouselItemForPosition(int position) {
        return new PageItem(CAROUSEL_IMAGE_RES[position % CAROUSEL_IMAGE_RES.length], getString(R.string.carousel_title_format, position));
    }

}
