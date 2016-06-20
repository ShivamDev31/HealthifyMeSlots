package com.shivamdev.healthifymedemo.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shivamchopra on 04/06/16.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public HealthifyMeApi getHealthifyMeApi() {
        return RetrofitAdapter.get().getRetrofit().create(HealthifyMeApi.class);
    }
}
