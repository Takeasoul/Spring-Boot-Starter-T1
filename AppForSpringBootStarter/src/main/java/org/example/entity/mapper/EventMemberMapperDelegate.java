package org.example.entity.mapper;



import jakarta.persistence.EntityNotFoundException;
import org.example.entity.EventMember;
import org.example.repositories.EventRepository;
import org.example.web.DTO.request.EventMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

public abstract class EventMemberMapperDelegate implements EventMemberMapper{

    @Autowired
    private EventMemberMapper delegate;

    @Autowired
    private EventRepository eventRepository;


    @Override
    public EventMember requestToEventMember(EventMemberRequest request){
        EventMember eventMember = delegate.requestToEventMember(request);
        eventMember.setEvent(eventRepository.findById(request.getEventId()).orElseThrow(() -> new EntityNotFoundException(
                MessageFormat.format("Event with ID {0} not found!", request.getEventId())
        )));

        return eventMember;
    }

}
