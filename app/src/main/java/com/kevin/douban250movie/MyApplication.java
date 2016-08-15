package com.kevin.douban250movie;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 描述: TODO
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-08-15 15:03
 */
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Fresco.initialize(this);
	}
}
