package com.nios.downloadfilelib.http;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * retrofit2封装工具类
 *
 * @author Charay
 * @version 2.0
 * @date 2018/12/25 15:19
 */

public class RetrofitManager {

    private final OkHttpClient mOkHttpClient;
    private final Retrofit mRetrofit;
    private final Api mApi;
    private static RetrofitManager INSTANCE;

    public static RetrofitManager getInstance() {
        return INSTANCE;
    }

    /**
     * 重置SiteIP
     */
    public static void reset(String baseUrl) {
        INSTANCE = new RetrofitManager(baseUrl);
        LogUtil.e("baseurl:"+baseUrl);
    }

    private RetrofitManager(String baseUrl) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        this.mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(logging)
                .build();

        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        LogUtil.e("baseurl:"+baseUrl);

        this.mApi = mRetrofit.create(Api.class);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public Api getApi() {
        return mApi;
    }

}