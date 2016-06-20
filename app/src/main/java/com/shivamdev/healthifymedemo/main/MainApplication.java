package com.shivamdev.healthifymedemo.main;

import android.app.Application;

import com.shivamdev.healthifymedemo.dagger.DaggerServiceComponent;
import com.shivamdev.healthifymedemo.dagger.ServiceComponent;


/**
 * Created by shivamchopra on 03/06/16.
 */
public class MainApplication extends Application {
    private static MainApplication instance;
    private ServiceComponent component;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerServiceComponent.builder().build();
    }

    public ServiceComponent component() {
        return component;
    }
}
