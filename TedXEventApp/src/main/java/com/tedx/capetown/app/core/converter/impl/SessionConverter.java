package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.app.core.models.TalkCollectionModel;
import com.tedx.capetown.lib.sdk.dto.SessionDTO;
import com.tedx.capetown.lib.sdk.dto.TalkCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.TalkDTO;

import java.util.List;

public class SessionConverter extends AbstractConverter<SessionDTO, SessionModel> implements Converter<SessionDTO,SessionModel>

{
    public SessionConverter(Class<SessionDTO> sourceClass, Class<SessionModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public SessionModel convert(SessionDTO source)
    {
        SessionModel model = new SessionModel();
        model.sessionTime = source.startTime;
        model.id = source.id;
        model.name = source.name;
        model.talks = new TalkCollectionConverter(List.class, TalkCollectionModel.class).convert(source.talks);
        return model;
    }

}