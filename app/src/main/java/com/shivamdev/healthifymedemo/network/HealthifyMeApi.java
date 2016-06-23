package com.shivamdev.healthifymedemo.network;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Shivam on 17-06-2016.
 */
public interface HealthifyMeApi {

    // http://108.healthifyme.com/api/v1/booking/slots/all?username=alok%40x.coz
    // &api_key=a4aeb4e27f27b5786828f6cdf00d8d2cb44fe6d7&vc=276&expert_username=neha%40healthifyme.com&format=json

    @GET("/api/v1/booking/slots/all")
    Observable<JsonElement> getSlots(@QueryMap Map<String, String>queryMap);
}
