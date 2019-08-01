package com.example.android.contactapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.contactapp.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class UniversalImageLoaer {
    private static final int DEFAULT_IMAGE = R.drawable.ic_android;
    private Context mContext;

    public UniversalImageLoaer(Context mContext) {
        this.mContext = mContext;
    }

    public ImageLoaderConfiguration getConfig(){
        // Universal Image Loader setup
        DisplayImageOptions defaultOption = new DisplayImageOptions.Builder()
                .showImageOnLoading(DEFAULT_IMAGE)
                .showImageForEmptyUri(DEFAULT_IMAGE)
                .showImageOnFail(DEFAULT_IMAGE)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOption)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024)
                .build();

        return  configuration;
    }

    public static void setImage(String imageUrl, ImageView imageView, final ProgressBar progressBar, String append){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imageUrl, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(progressBar != null){
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
