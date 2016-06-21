package com.shivamdev.healthifymedemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shivamdev.healthifymedemo.R;
import com.shivamdev.healthifymedemo.fragments.adapters.DateSlotsAdapter;
import com.shivamdev.healthifymedemo.main.CommonUtils;
import com.shivamdev.healthifymedemo.main.Constants;
import com.shivamdev.healthifymedemo.main.LogToast;
import com.shivamdev.healthifymedemo.main.MainApplication;
import com.shivamdev.healthifymedemo.network.data.SlotsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Shivam on 20-06-2016.
 */
public class BookingFragment extends Fragment {

    private static final String TAG = BookingFragment.class.getSimpleName();

    //vc=276&expert_username=neha%40healthifyme.com&format=json
    private static final String VC = "vc";
    private static final String VC_ID = "276";
    private static final String USERNAME = "username";
    private static final String USER = "alok@x.coz";

    private static final String EXPERT_USERNAME = "expert_username";
    private static final String EXPERT_NAME = "neha@healthifyme.com";
    private static final String FORMAT = "format";
    private static final String JSON = "json";

    private CompositeSubscription compositeSubscription;

    private SwipeRefreshLayout srlRefresh;
    private ProgressBar pbLoader;
    private LinearLayout llError;
    private LinearLayout llBookingLayout;
    private TextView tvErrorMessage;
    private TabLayout tlDates;
    private ViewPager vpDates;
    private TextView tvMonth;

    private DateSlotsAdapter adapter;

    public static BookingFragment newInstance() {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.booking_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compositeSubscription = new CompositeSubscription();
        tlDates = (TabLayout) view.findViewById(R.id.tl_dates);
        vpDates = (ViewPager) view.findViewById(R.id.vp_slots);
        tvMonth = (TextView) view.findViewById(R.id.tv_month);
        llBookingLayout = (LinearLayout) view.findViewById(R.id.ll_booking);
        llError = (LinearLayout) view.findViewById(R.id.ll_error_layout);
        tvErrorMessage = (TextView) view.findViewById(R.id.tv_error_text);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
        srlRefresh.setColorSchemeColors(getActivity().getResources().getColor(R.color.color_accent));
        srlRefresh.setOnRefreshListener(new RefreshData());
        pbLoader = (ProgressBar) view.findViewById(R.id.pb_loader);
        adapter = new DateSlotsAdapter(getChildFragmentManager());

        vpDates.setAdapter(adapter);
        tlDates.setupWithViewPager(vpDates);

        getSlotsFromServer();
    }

    private void getSlotsFromServer() {
        Map<String, String> query = new HashMap<>();
        query.put(VC, VC_ID);
        query.put(EXPERT_USERNAME, EXPERT_NAME);
        query.put(Constants.KEY, Constants.AUTH_KEY);
        query.put(FORMAT, JSON);
        query.put(USERNAME, USER);

        showLoader(true);

        Subscription subs = MainApplication.getInstance().component().getHealthifyMeApi().getSlots(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SlotsData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogToast.log(TAG, "onError: " + e);
                        showError(true);
                    }

                    @Override
                    public void onNext(SlotsData json) {
                        showLoader(false);

                        setDataOnUi(json);
                    }
                });
        compositeSubscription.add(subs);
    }

    private void setDataOnUi(SlotsData json) {
        Set<String> dateKeys = json.slots.keySet();

        tvMonth.setText(CommonUtils.convertDateToString((String) dateKeys.toArray()[0], true));

        List<String> dateSlots = new ArrayList<>();
        List<SlotsData.Slots> slots = new ArrayList<>();

        for (String key : json.slots.keySet()) {
            dateSlots.add(CommonUtils.convertDateToString(key, false));
        }

        for (int i = 0; i < json.slots.size(); i++) {
            slots.add(json.slots.get(i));
        }

        adapter.refreshSlots(dateSlots, slots);

    }

    private class RefreshData implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            srlRefresh.setRefreshing(true);
            getSlotsFromServer();
        }
    }

    private void showError(boolean isVisible) {
        if (isVisible) {
            llError.setVisibility(View.VISIBLE);
            pbLoader.setVisibility(View.GONE);
        } else {
            llError.setVisibility(View.GONE);
            llBookingLayout.setVisibility(View.VISIBLE);
        }
    }

    private void showLoader(boolean isVisible) {
        if (isVisible) {
            pbLoader.setVisibility(View.VISIBLE);
            llError.setVisibility(View.GONE);
            llBookingLayout.setVisibility(View.GONE);
        } else {
            pbLoader.setVisibility(View.GONE);
            llBookingLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
