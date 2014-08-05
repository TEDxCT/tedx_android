package com.tedx.capetown.app.presentation.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.app.presentation.activity.TalkActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        if(mSessionsListModel.get(i).isSession())
        {
            currentView = mInflater.inflate(R.layout.listview_session, viewGroup, false);
            SessionModel sessionModel = mSessionsListModel.get(i).sessionModel;
            // Bug with timezones from the Server
            Date sessionDate = new Date(Long.parseLong(sessionModel.sessionTime)*1000);
            sessionDate = new Date(sessionDate.getTime()+sessionDate.getTimezoneOffset()*60*1000);
            ((TextView)currentView.findViewById(R.id.listview_session_time)).setText(new SimpleDateFormat("kk:mm").format(sessionDate));
            ((TextView)currentView.findViewById(R.id.listview_session_name)).setText(sessionModel.name);
            ((TextView)currentView.findViewById(R.id.listview_session_position)).setText("Session "+sessionModel.position);
        }
        else {
            currentView = mInflater.inflate(R.layout.listview_session_talk, viewGroup, false);
            final TalkModel talkModel = mSessionsListModel.get(i).talkModel;
            ((TextView) currentView.findViewById(R.id.listview_session_name)).setText(talkModel.name);
            ((TextView)currentView.findViewById(R.id.txtSpeaker)).setText(talkModel.speaker.fullName);
            ImageLoader.getInstance().displayImage(talkModel.imageURL, ((ImageView) currentView.findViewById(R.id.imgSessionSpeaker)));
            currentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,TalkActivity.class);
                    intent.putExtra("talkId",talkModel.id);
                    context.startActivity(intent);
                }
            });
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
