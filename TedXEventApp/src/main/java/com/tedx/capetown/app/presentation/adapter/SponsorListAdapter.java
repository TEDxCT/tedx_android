package com.tedx.capetown.app.presentation.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.SponsorModel;

import java.util.List;

public class SponsorListAdapter extends BaseAdapter {
    List<SponsorModel> mSponsorModelList;
    Context mContext;

    public SponsorListAdapter(List<SponsorModel> sponsorModelList, Context context)
    {
        mSponsorModelList = sponsorModelList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mSponsorModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSponsorModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mSponsorModelList.get(i).id;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View currentView = mInflater.inflate(R.layout.activity_sponsors, viewGroup, false);
       // ((ImageView) currentView.findViewById(R.id.imgSponsor)).setImageDrawable(mSponsorModelList.get(i).ImageURL);
        // TODO get image loader from html thingy to populate image
        return currentView;
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 100;
    }

    @Override
    public boolean isEmpty() {
        return mSponsorModelList.isEmpty();
    }

    public void updateData(List<SponsorModel> sponsors) {
        mSponsorModelList = sponsors;
    }

}