package org.example.entity.mapper;



import org.example.entity.Event;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

public abstract class EventMapperDelegate implements EventMapper {

    @Autowired
    private EventMapper delegate;


    @Override
    public EventResponse eventToResponse(Event event){

        return delegate.eventToResponse(event);
    }

    @Override
    public Event requestToEvent(EventRequest request){
        return delegate.requestToEvent(request);
    }

}
