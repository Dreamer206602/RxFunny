package com.mx.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by boobooL on 2016/4/28 0028
 * Created 邮箱 ：boobooMX@163.com
 */
public class RxService {
    public static final String BASERESULTURL="http://apis.baidu.com/showapi_open_bus/";
    private static OkHttpClient sOkHttpClient=new OkHttpClient.Builder().addInterceptor
            (new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    private static Retrofit sRetrofit=new Retrofit.Builder()
            .baseUrl(BASERESULTURL)
            .client(sOkHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RxService() {
    }

    public static <T> T createApi(Class<T> tClass){
        return sRetrofit.create(tClass);
    }
}
