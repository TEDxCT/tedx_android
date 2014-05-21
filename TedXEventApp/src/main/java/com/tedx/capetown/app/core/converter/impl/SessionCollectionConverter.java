package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SessionCollectionModel;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.lib.sdk.dto.SessionCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SessionDTO;

import java.util.ArrayList;

public class SessionCollectionConverter extends AbstractConverter<SessionCollectionDTO, SessionCollectionModel> implements Converter<SessionCollectionDTO,SessionCollectionModel> {

    public SessionCollectionConverter(Class<SessionCollectionDTO> sourceClass, Class<SessionCollectionModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public SessionCollectionModel convert(SessionCollectionDTO source)
    {
        SessionCollectionModel sessionCollectionModel = new SessionCollectionModel();
        sessionCollectionModel.sessions = new ArrayList<SessionModel>();

        SessionConverter sessionConverter = new SessionConverter(SessionDTO.class, SessionModel.class);
        for(SessionDTO session : source.getCollection())
            sessionCollectionModel.sessions.add(sessionConverter.convert(session));

        return sessionCollectionModel;
    }

}