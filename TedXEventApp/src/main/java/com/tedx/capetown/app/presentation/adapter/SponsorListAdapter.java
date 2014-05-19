package com.tedx.capetown.app.presentation.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.SponsorModel;

import java.util.List;

public class SponsorListAdapter implements ListAdapter {
    List<SponsorModel> mSponsorModelList;
    Context mContext;

    public SponsorListAdapter(List<SponsorModel> sponsorModelList, Context context)
    {
        mSponsorModelList = sponsorModelList;
        mContext = context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        //LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View currentView = inflater.inflate(R.layout.listview_sponsor, viewGroup, false);
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
}
