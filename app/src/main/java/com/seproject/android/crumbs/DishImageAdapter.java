package com.seproject.android.crumbs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 *
 * Adapter for Dish Images
 */

public class DishImageAdapter extends BaseAdapter {
    private Context mContext;

    public DishImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.chicken_dish, R.drawable.paneer_capsicum,
            R.drawable.fried, R.drawable.noodles_dish
    };

    //Reference to name of the images
    public String[] mThumbNames = {
            "Chicken","Paneer",
            "Fried Rice","Noodels"
    };

    public Integer[] mDishPrice = {
            8, 5, 7, 4
    };

}
