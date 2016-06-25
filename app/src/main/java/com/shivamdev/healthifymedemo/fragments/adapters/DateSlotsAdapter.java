package com.shivamdev.healthifymedemo.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shivamdev.healthifymedemo.fragments.BookingSlotsFragment;
import com.shivamdev.healthifymedemo.main.CommonUtils;
import com.shivamdev.healthifymedemo.network.data.MyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivam on 20-06-2016.
 */
public class DateSlotsAdapter extends FragmentPagerAdapter {

    List<MyData.Data> dateSlots;

    public DateSlotsAdapter(FragmentManager fm) {
        super(fm);
        dateSlots = new ArrayList<>();
    }

    public void refreshSlots(List<MyData.Data> dates) {
        dateSlots.clear();
        dateSlots.addAll(dates);

        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        BookingSlotsFragment slotsFragment = BookingSlotsFragment.newInstance(position, dateSlots.get(position).daySlots);
        return slotsFragment;
    }

    @Override
    public int getCount() {
        return dateSlots.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return CommonUtils.convertDateToString(dateSlots.get(position).date, false);
    }
}
