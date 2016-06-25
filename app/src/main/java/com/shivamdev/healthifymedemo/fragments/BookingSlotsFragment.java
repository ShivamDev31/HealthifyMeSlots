package com.shivamdev.healthifymedemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.shivamdev.healthifymedemo.R;
import com.shivamdev.healthifymedemo.fragments.adapters.SlotsAdapter;
import com.shivamdev.healthifymedemo.network.data.SlotsData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shivam on 20-06-2016.
 */
public class BookingSlotsFragment extends Fragment {

    private static final String POS = "pos";
    private static final String SLOTS = "slots";

    private RecyclerView rvSlots;
    private SlotsAdapter adapter;

        public static BookingSlotsFragment newInstance(int position, List<SlotsData.Slots> slots) {
        BookingSlotsFragment fragment = new BookingSlotsFragment();
        Bundle args = new Bundle();
        args.putInt(POS, position);
        args.putSerializable(SLOTS, (Serializable) slots);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slots_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SlotsData.Slots> slots = (List<SlotsData.Slots>) getArguments().getSerializable(SLOTS);

        rvSlots = (RecyclerView) view.findViewById(R.id.rv_slots);
        adapter = new SlotsAdapter(slots);

        RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(savedInstanceState);

        rvSlots.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSlots.setAdapter(expMgr.createWrappedAdapter(adapter));

        expMgr.attachRecyclerView(rvSlots);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
