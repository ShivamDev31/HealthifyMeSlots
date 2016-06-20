package com.shivamdev.healthifymedemo.activities;

import android.os.Bundle;

import com.shivamdev.healthifymedemo.R;
import com.shivamdev.healthifymedemo.fragments.BookingFragment;
import com.shivamdev.healthifymedemo.main.LogToast;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbar(getString(R.string.app_name));
        addBookingFragment();
    }

    private void addBookingFragment() {
        BookingFragment bookingFragment = BookingFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_fragment, bookingFragment, TAG)
                .commit();
    }
}
