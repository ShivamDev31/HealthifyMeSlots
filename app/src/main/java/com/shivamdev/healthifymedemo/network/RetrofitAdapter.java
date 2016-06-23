package com.shivamdev.healthifymedemo.network;

import com.shivamdev.healthifymedemo.main.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by shivamchopra on 04/06/16.
 */
public class RetrofitAdapter {
    private static final String TAG = RetrofitAdapter.class.getSimpleName();

    private Retrofit retrofit;
    private static RetrofitAdapter adapter;

    private RetrofitAdapter() {
        retrofit = retrofit();
    }

    public static synchronized RetrofitAdapter get() {
        if (adapter == null) {
            adapter = new RetrofitAdapter();
        }
        return adapter;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.URLS.BASE_URL)
                .client(getOkHttpClient())
                //.addConverterFactory(ScalarsConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create(GsonUtil.getInstance().getGson()))
                .addConverterFactory(new ToStringConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return client;
    }
}
