package com.shivamdev.healthifymedemo.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;
import com.shivamdev.healthifymedemo.R;
import com.shivamdev.healthifymedemo.network.data.SlotsData;

import java.util.List;

/**
 * Created by Shivam on 21-06-2016.
 */
public class SlotsAdapter extends AbstractExpandableItemAdapter<MyGroupViewHolder, MyChildViewHolder> {

    List<SlotsData.Slots> slotsData;

    public SlotsAdapter(List<SlotsData.Slots> slots) {
        setHasStableIds(true);
        slotsData = slots;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildCount(int groupPosition) {
        return slotsData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collapsed_slot_item, parent, false);
        return new MyGroupViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expanded_slot_item, parent, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        //holder.tvDaySlot.setText(slotsData.get(0).afternoon.get(groupPosition));
        holder.tvNoOfSlots.setText(slotsData.get(0).afternoon.size());
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {
        String startTime = slotsData.get(0).afternoon.get(childPosition).startTime;
        String endTime = slotsData.get(0).afternoon.get(childPosition).endTime;
        holder.tvSlot.setText(startTime + " : " + endTime);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return false;
    }
}

class MyGroupViewHolder extends AbstractExpandableItemViewHolder {
    TextView tvDaySlot;
    TextView tvNoOfSlots;
    ImageView ivArrow;
    public MyGroupViewHolder(View view) {
        super(view);
        tvDaySlot = (TextView) view.findViewById(R.id.tv_day_time);
        tvNoOfSlots = (TextView) view.findViewById(R.id.tv_slots_available);
        ivArrow = (ImageView) view.findViewById(R.id.iv_arrow);
    }
}

class MyChildViewHolder extends AbstractExpandableItemViewHolder {
    TextView tvSlot;
    public MyChildViewHolder(View view) {
        super(view);
        tvSlot = (TextView) view.findViewById(R.id.tv_time_slot);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.light_grey));
            }
        });
    }
}