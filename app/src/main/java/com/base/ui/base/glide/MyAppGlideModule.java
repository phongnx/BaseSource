package com.base.ui.base.glide;

import android.content.Context;

import com.base.R;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by admin on 8/8/2017.
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//        int memoryCacheSizeBytes = 1024 * 1024 * 50; // 50mb
//        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100mb
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, context.getString(R.string.app_name), diskCacheSizeBytes));

    }
}
