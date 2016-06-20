package com.shivamdev.healthifymedemo.dagger;

import com.shivamdev.healthifymedemo.network.HealthifyMeApi;
import com.shivamdev.healthifymedemo.network.NetworkModule;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by shivamchopra on 04/06/16.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface ServiceComponent {

    HealthifyMeApi getHealthifyMeApi();
}
