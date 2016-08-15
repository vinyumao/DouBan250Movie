package com.kevin.douban250movie;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述: TODO
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-08-12 18:07
 */
public class RetrofitManager {
	private static RetrofitManager instance;
	private Retrofit retrofit;
	private OkHttpClient client;

	private RetrofitManager(){
		client = new OkHttpClient.Builder()
				.connectTimeout(5, TimeUnit.SECONDS)
				.readTimeout(10,TimeUnit.SECONDS)
				.build();

		retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.client(client)
				.build();
	}

	public static RetrofitManager getInstance(){
		if (instance == null) {
			synchronized (RetrofitManager.class){
				instance = new RetrofitManager();
			}
		}
		return instance;
	}

	public <T> T create(Class<T> servise){
		return retrofit.create(servise);
	}




	public static String baseUrl = "https://api.douban.com/v2/movie/";
}
