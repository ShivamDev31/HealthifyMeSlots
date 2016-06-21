package com.shivamdev.healthifymedemo.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shivam on 20-06-2016.
 */
public class SlotsData {

    @SerializedName("slots")
    public Map<String, Slots> slots = new HashMap<>();

    public static class Slots {

        @SerializedName("morning")
        public List<TimingData> morning;

        @SerializedName("afternoon")
        public List<TimingData> afternoon;

        @SerializedName("evening")
        public List<TimingData> evening;
    }

    public class TimingData {

        @SerializedName("start_time")
        public String startTime;

        @SerializedName("end_time")
        public String endTime;
    }
}