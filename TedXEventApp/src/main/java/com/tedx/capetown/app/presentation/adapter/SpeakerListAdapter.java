package com.tedx.capetown.app.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.presentation.activity.SpeakerProfileActivity;
import com.tedx.capetown.app.presentation.activity.TalkActivity;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/01.
 */
public class SpeakerListAdapter extends BaseAdapter {
    List<SpeakerModel> mSpeakerModelList;
    Context context;
    public SpeakerListAdapter(List<SpeakerModel> mSpeakerModelList, Context context)
    {
        this.mSpeakerModelList = mSpeakerModelList;
        this.context = context;
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
        return mSpeakerModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSpeakerModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mSpeakerModelList.get(i).id;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final SpeakerModel speakerModel = mSpeakerModelList.get(i);
        View currentView = mInflater.inflate(R.layout.listview_speaker_details, viewGroup, false);
        ((TextView)currentView.findViewById(R.id.speaker_name)).setText(speakerModel.fullName);
        ((TextView)currentView.findViewById(R.id.speaker_description)).setText(Html.fromHtml(speakerModel.descriptionHTML));
        ImageLoader.getInstance().displayImage(speakerModel.imageURL, ((ImageView) currentView.findViewById(R.id.speaker_image)));
        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SpeakerProfileActivity.class);
                intent.putExtra("speakerId", speakerModel.id);
                context.startActivity(intent);
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
        return mSpeakerModelList.isEmpty();
    }

    public void updateData(List<SpeakerModel> speakers) {
        mSpeakerModelList = speakers;
    }
}
