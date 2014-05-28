package com.tedx.capetown.app.core.models;

/**
 * Created by andrewpettey on 2014/05/28.
 */
public class SessionsListModel {
    public SessionModel sessionModel = null;
    public TalkModel talkModel = null;
    public boolean child = false;

    public boolean isSession()
    {
     return child;
    }
}
