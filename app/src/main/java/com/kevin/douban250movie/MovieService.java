package com.kevin.douban250movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 描述: TODO
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-08-12 18:05
 */
public interface MovieService {
	@GET("top250")
	Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}
