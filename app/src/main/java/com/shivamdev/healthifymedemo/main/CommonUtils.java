package com.shivamdev.healthifymedemo.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by shivamchopra on 04/06/16.
 */
public class CommonUtils {

    public static void loadImage(Context context, String imageUrl, int placeHolder, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl)) {
            Picasso.with(context).load(placeHolder).into(imageView);
            return;
        }
        Picasso.with(context).load(imageUrl).fit().centerCrop().placeholder(placeHolder).into(imageView);
    }

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(MainApplication.getInstance().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
