package com.tedx.capetown.app.presentation.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.app.core.models.SessionsListModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.app.presentation.adapter.SessionListAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class AgendaFragment extends ListFragment {

    SessionListAdapter _listAdapter;
    private OnFragmentInteractionListener _listener;

    static final String[] PROJECTION = new String[]{ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME}; // These are the Contacts rows that we will retrieve
    static final String SELECTION = "((" + ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" + ContactsContract.Data.DISPLAY_NAME + " != '' ))";


    public static AgendaFragment newInstance() {
        return new AgendaFragment();
    }

    public AgendaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_agenda, container, false);
        return fragmentView;
    }

    public void onButtonPressed(Uri uri) {
        if (_listener != null)
            _listener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) getView().findViewById(android.R.id.content);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data. We pass null for the cursor, then update it in onLoadFinished()
        SessionListAdapter sessionListAdapter = new SessionListAdapter(getModel(), getActivity());
        setListAdapter(sessionListAdapter);

        // Prepare the loader.  Either re-connect with an existing one, or start a new one.
//        getLoaderManager().initLoader(0, null, this);
    }

    public List<SessionsListModel> getModel() {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        List<SessionModel> sessions = (List<SessionModel>) eventCollectionModel.events.get(0).sessions.sessions;
        List<SessionsListModel> sessionsList = new ArrayList<SessionsListModel>();
        for (SessionModel session : sessions) {
            SessionsListModel tempSession = new SessionsListModel();
            tempSession.sessionModel = session;
            sessionsList.add(tempSession);
            for (TalkModel talk : session.talks.talks) {
                SessionsListModel tempTalk = new SessionsListModel();
                tempTalk.talkModel = talk;
                tempTalk.child = true;
                sessionsList.add(tempTalk);
            }
        }
        // apply logic to get a model
        return sessionsList;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

}