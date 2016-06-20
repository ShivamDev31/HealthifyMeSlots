package com.shivamdev.healthifymedemo.network.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by Shivam on 20-06-2016.
 */
public class SlotsData {

    @SerializedName("slots")
    public JSONObject slots;

    public static class Slots {

        @SerializedName("morning")
        public String morning;

        @SerializedName("afternoon")
        public String afternoon;

        @SerializedName("evening")
        public String evening;
    }

    public class TimingData {

        @SerializedName("start_time")
        public String startTime;

        @SerializedName("end_time")
        public String endTime;
    }
}
