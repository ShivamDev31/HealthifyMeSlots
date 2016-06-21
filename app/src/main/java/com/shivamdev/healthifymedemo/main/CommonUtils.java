package com.shivamdev.healthifymedemo.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shivamchopra on 04/06/16.
 */
public class CommonUtils {

    public static String convertDateToString(String date, boolean isMonth) {
        DateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = null;
        try {
            currentDate = sd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        if (isMonth) {
            return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        }
        return cal.get(Calendar.DAY_OF_MONTH) + "\n" + cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(MainApplication.getInstance().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
