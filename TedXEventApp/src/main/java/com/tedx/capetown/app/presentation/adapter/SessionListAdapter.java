package com.tedx.capetown.app.presentation.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.app.core.models.SessionsListModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.TalkModel;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/01.
 */
public class SessionListAdapter extends BaseAdapter {
    List<SessionsListModel> mSessionsListModel;
    Context context;
    public SessionListAdapter(List<SessionsListModel> mSessionsListModel, Context context)
    {
        this.mSessionsListModel = mSessionsListModel;
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
        return mSessionsListModel.size();
    }

    @Override
    public Object getItem(int i) {
        return mSessionsListModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View currentView = null;
        if(!mSessionsListModel.get(i).isSession())
        {
            currentView = mInflater.inflate(R.layout.listview_session, viewGroup, false);
            SessionModel sessionModel = mSessionsListModel.get(i).sessionModel;
            ((TextView)currentView.findViewById(R.id.text_name)).setText(sessionModel.name);
//            String formattedTime = sessionModel.startTime+mSessionsListModel.get(i).sessionModel.endTime;
//            ((TextView)currentView.findViewById(R.id.speaker_name)).setText(formattedTime);
        }
        else {
            currentView = mInflater.inflate(R.layout.listview_session_talk, viewGroup, false);
            TalkModel talkModel = mSessionsListModel.get(i).talkModel;
                    ((TextView) currentView.findViewById(R.id.text_name)).setText(talkModel.name);
//            ((TextView)currentView.findViewById(R.id.speaker_name)).setText(mSessionsListModel.get(i).fullName);
//            ((TextView)currentView.findViewById(R.id.speaker_name)).setText(mSessionsListModel.get(i).fullName);
//            ((TextView)currentView.findViewById(R.id.speaker_name)).setText(mSessionsListModel.get(i).fullName);
//            ((TextView)currentView.findViewById(R.id.speaker_name)).setText(mSessionsListModel.get(i).fullName);
            ImageLoader.getInstance().displayImage(talkModel.imageURL, ((ImageView) currentView.findViewById(R.id.imgSessionSpeaker)));
        }
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
        return mSessionsListModel.isEmpty();
    }

    public void updateData(List<SessionsListModel> sessionsListModels) {
        mSessionsListModel = sessionsListModels;
    }
}
