package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SessionCollectionModel;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.lib.sdk.dto.SessionCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SessionDTO;

import java.util.ArrayList;
import java.util.List;

public class SessionCollectionConverter extends AbstractConverter<List, SessionCollectionModel> implements Converter<List<SessionDTO>,SessionCollectionModel> {

    public SessionCollectionConverter(Class<List> sourceClass, Class<SessionCollectionModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public SessionCollectionModel convert(List<SessionDTO> source)
    {
        SessionCollectionModel sessionCollectionModel = new SessionCollectionModel();
        sessionCollectionModel.sessions = new ArrayList<SessionModel>();

        SessionConverter sessionConverter = new SessionConverter(SessionDTO.class, SessionModel.class);
        for(SessionDTO session : source)
            sessionCollectionModel.sessions.add(sessionConverter.convert(session));

        return sessionCollectionModel;
    }

}