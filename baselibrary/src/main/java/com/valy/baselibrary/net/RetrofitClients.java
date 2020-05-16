package com.valy.baselibrary.net;


import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.valy.baselibrary.utils.application.BaseApp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClients {
    private static final String API_HOST = "http://zhongbyi.aitecc.com/mobile/";
    private static RetrofitClients instance;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private RetrofitClients() {

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(BaseApp.getContext()));

        try {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .cookieJar(cookieJar)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .addInterceptor(InterceptorUtil.logInterceptor())
                    .addInterceptor(InterceptorUtil.headerInterceptor())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }


        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())   // 使用 gson 解析器解析 json
                .client(okHttpClient)
                .build();
    }

    public static RetrofitClients getInstance() {
        if (instance == null) {
            synchronized (RetrofitClients.class) {
                if (instance == null) {
                    instance = new RetrofitClients();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}