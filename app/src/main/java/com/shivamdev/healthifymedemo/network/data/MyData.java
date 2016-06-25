package com.shivamdev.healthifymedemo.network.data;

import com.google.gson.annotations.SerializedName;
import com.shivamdev.healthifymedemo.main.LogToast;
import com.shivamdev.healthifymedemo.network.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Shivam on 22-06-2016.
 */

public class MyData {
    private static final String TAG = MyData.class.getSimpleName();

    private JSONObject jsonObject;

    public void JsonParser(String json) {
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Data> parseDates() {
        JSONObject dateSlots = null;
        List<Data> myData = new ArrayList<>();
        List<Slots> mySlots = new ArrayList<>();
        try {
            dateSlots = jsonObject.getJSONObject("slots");

            Iterator dates = dateSlots.keys();

            while (dates.hasNext()) {
                String date = (String) dates.next();

                Slots slot = GsonUtil.getInstance().getGson().fromJson(dateSlots.getJSONObject(date).toString(), Slots.class);
                mySlots.add(slot);

                Data data = new Data();
                data.date = date;
                data.daySlots = mySlots;
                myData.add(data);
            }

            for (int i = 0; i < dateSlots.length(); i++) {


            }
        } catch (JSONException e) {
            LogToast.log(TAG, "parseDates: catch : " + e);
        }

        return myData;
    }

    public static class Data {
        public String date;
        public List<Slots> daySlots;
    }

    public static class Slots implements Serializable {

        @SerializedName("afternoon")
        public List<TimingData> afternoon;

        @SerializedName("evening")
        public List<TimingData> evening;

        @SerializedName("morning")
        public List<TimingData> morning;
    }

    public class TimingData {

        @SerializedName("start_time")
        public String startTime;

        @SerializedName("end_time")
        public String endTime;

        @SerializedName("slot_id")
        public int slotId;
    }
}
