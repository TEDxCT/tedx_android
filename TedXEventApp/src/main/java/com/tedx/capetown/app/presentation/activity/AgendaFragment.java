package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;

import de.greenrobot.event.EventBus;

public class AgendaFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{

    SimpleCursorAdapter _listAdapter;
    private OnFragmentInteractionListener _listener;

    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,  ContactsContract.Data.DISPLAY_NAME}; // These are the Contacts rows that we will retrieve
    static final String SELECTION = "((" +  ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +  ContactsContract.Data.DISPLAY_NAME + " != '' ))";


    public static AgendaFragment newInstance()
    {
        AgendaFragment fragment = new AgendaFragment();
        return fragment;
    }

    public AgendaFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_agenda, container, false);
        return fragmentView;
    }

    public void onButtonPressed(Uri uri)
    {
        if (_listener != null)
            _listener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            _listener = (OnFragmentInteractionListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) getView().findViewById(android.R.id.content);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data. We pass null for the cursor, then update it in onLoadFinished()
        _listAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
        setListAdapter(_listAdapter);

        // Prepare the loader.  Either re-connect with an existing one, or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        _listener = null;
    }

    public interface OnFragmentInteractionListener
    {
        public void onFragmentInteraction(Uri uri);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        // Now create and return a CursorLoader that will take care of creating a Cursor for the data being displayed.
        return new CursorLoader(getActivity(), ContactsContract.Data.CONTENT_URI, PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        // Swap the new cursor in.  (The framework will take care of closing the old cursor once we return.)
        _listAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader)
    {
        // This is called when the last Cursor provided to onLoadFinished() above is about to be closed.  We need to make sure we are no longer using it.
        _listAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
    }

}