package com.mx.api;

import com.mx.model.JokeEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public interface JokeApi {
    @Headers("apikey:83ec99fff780989a5376a1bc595ed5ff")
    @GET("showapi_joke/joke_text")
    Observable<JokeEntity>getJoke(@Query("page")int page);

    @GET("showapi_joke/joke_text")
    Call<JokeEntity>callJoke(@Header("apikey") String apiKey, @Query("page")int page);

}
