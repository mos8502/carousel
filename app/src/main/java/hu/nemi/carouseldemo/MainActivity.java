package hu.nemi.carouseldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import hu.nemi.carousel.CarouselView;
import hu.nemi.carousel.R;

public class MainActivity extends AppCompatActivity {
    static final int INITIAL_CAROUSEL_ITEM_COUNT = 2;
    static final int[] CAROUSEL_IMAGE_RES = {
            R.drawable.carousel_image_1,
            R.drawable.carousel_image_2,
            R.drawable.carousel_image_3,
            R.drawable.carousel_image_4
    };

    CarouselView carouselView;
    Adapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = (CarouselView) findViewById(R.id.carousel_container);
        itemAdapter = new Adapter(R.layout.item_view);
        itemAdapter.setItems(createCarouselItems(INITIAL_CAROUSEL_ITEM_COUNT));
        carouselView.setAdapter(itemAdapter);
        carouselView.setCurrentItem(0, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_item : {
                itemAdapter.setItems(createCarouselItems(itemAdapter.getCount() + 1));
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    List<PageItem> createCarouselItems(int count) {
        List<PageItem> carouselItemList = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            carouselItemList.add(getCarouselItemForPosition(i));
        }
        return carouselItemList;
    }

    PageItem getCarouselItemForPosition(int position) {
        return new PageItem(CAROUSEL_IMAGE_RES[position % CAROUSEL_IMAGE_RES.length], getString(R.string.carousel_title_format, position));
    }

}
