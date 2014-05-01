package com.tedx.capetown.app.presentation.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.tedx.capetown.app.core.models.SpeakerModel;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/01.
 */
public class SpeakerListAdapter implements ListAdapter {
    List<SpeakerModel> mSpeakerModelList;
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
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mSpeakerModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mSpeakerModelList.get(i).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
