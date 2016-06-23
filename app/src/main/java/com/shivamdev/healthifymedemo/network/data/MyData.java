package com.shivamdev.healthifymedemo.network.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Shivam on 22-06-2016.
 */
public class MyData {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
    private JSONObject jsonObject;

    public void JsonParser(String json) {
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public List<Weather> parseWeather() throws JSONException, ParseException {
        JSONArray slots = jsonObject.getJSONArray("slots");
        for (int i = 0; i < slots.length(); i++) {
            JSONObject jsonObject = slots.getJSONObject(i);
            String date = jsonObject.keys().next();
            JSONObject data = jsonObject.getJSONObject(date);

            Weather weather = new Weather();
            weather.date = simpleDateFormat.parse(date);
            weather.dayWeather = null; // gson code DayWeather
        }
        return null;
    }


    public static class Weather {
        private Date date;
        private Slots dayWeather;
    }


    public static class Slots {

        @SerializedName("afternoon")
        private TimingData[] afternoon;

        @SerializedName("evening")
        private TimingData[] evening;

        @SerializedName("morning")
        private TimingData[] morning;
    }

    public class TimingData {

        @SerializedName("start_time")
        public String startTime;

        @SerializedName("end_time")
        public String endTime;
    }
}
