package com.tedx.capetown.app.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.app.presentation.activity.SpeakerProfileActivity;

import java.util.List;

public class SponsorListAdapter extends BaseAdapter
{

    List<SponsorModel> _sponsorModelList;
    Context _context;

    public SponsorListAdapter(List<SponsorModel> sponsorModelList, Context context)
    {
        _sponsorModelList = sponsorModelList;
        _context = context;
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
    public void registerDataSetObserver(DataSetObserver dataSetObserver)
    {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver)
    {
    }

    @Override
    public int getCount() {
        return _sponsorModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return _sponsorModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _sponsorModelList.get(i).id;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final SponsorModel sponsorModel = _sponsorModelList.get(i);
        View currentView = layoutInflater.inflate(R.layout.listview_sponsor, viewGroup, false);
        ImageLoader.getInstance().displayImage(sponsorModel.ImageURL, ((ImageView) currentView.findViewById(R.id.imgSponsor)));
        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlIntent(sponsorModel.WebsiteURL);
            }
        });
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
        return _sponsorModelList.isEmpty();
    }

    public void updateData(List<SponsorModel> sponsors) {
        _sponsorModelList = sponsors;
    }

    public void urlIntent(String url) {
        if(!url.startsWith("http://")&&!url.startsWith("https://"))
        {
            url = "http://"+url;
        }
        Uri uri = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
        _context.startActivity(webIntent);
    }
}