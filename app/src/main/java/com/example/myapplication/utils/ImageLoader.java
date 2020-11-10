package com.example.myapplication.utils;

import android.app.Activity;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by yang
 * date: 2020/8/19
 * Describe:
 */
public interface ImageLoader extends Serializable {

    void displayImage(Activity activity, String path, ImageView imageView, int width, int height);

    void clearMemoryCache();
}
