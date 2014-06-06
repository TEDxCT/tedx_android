package com.tedx.capetown.app.presentation.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.presentation.activity.AgendaFragment;
import com.tedx.capetown.app.presentation.activity.SpeakersFragment;

import java.util.Locale;

public class TabSectionsPagerAdapter extends FragmentPagerAdapter
{

    Context _context;

    public TabSectionsPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        _context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
           return AgendaFragment.newInstance();
      //  if (position == 1)
      //      return SpeakersFragment.newInstance();
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return _context.getString(R.string.title_section1);
            case 1:
                return _context.getString(R.string.title_section2);
            case 2:
                return _context.getString(R.string.title_section3);
        }
        return null;
    }





    public static class PlaceholderFragment extends Fragment
    {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            return rootView;
        }
    }


}