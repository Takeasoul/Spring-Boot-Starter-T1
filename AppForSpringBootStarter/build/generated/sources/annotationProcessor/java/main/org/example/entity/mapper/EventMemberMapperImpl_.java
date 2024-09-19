package org.example.entity.mapper;

import java.util.UUID;
import javax.annotation.processing.Generated;
import org.example.entity.Event;
import org.example.entity.EventMember;
import org.example.web.DTO.request.EventMemberRequest;
import org.example.web.DTO.response.EventMemberResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T19:19:31+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class EventMemberMapperImpl_ implements EventMemberMapper {

    @Override
    public EventMember requestToEventMember(EventMemberRequest request) {
        if ( request == null ) {
            return null;
        }

        EventMember eventMember = new EventMember();

        eventMember.setFirstname( request.getFirstname() );
        eventMember.setMiddlename( request.getMiddlename() );
        eventMember.setLastname( request.getLastname() );
        eventMember.setCompany( request.getCompany() );
        eventMember.setPosition( request.getPosition() );
        eventMember.setEmail( request.getEmail() );
        eventMember.setPhone( request.getPhone() );

        return eventMember;
    }

    @Override
    public EventMemberResponse eventMemberToResponse(EventMember eventMember) {
        if ( eventMember == null ) {
            return null;
        }

        EventMemberResponse eventMemberResponse = new EventMemberResponse();

        eventMemberResponse.setEventId( eventMemberEventId( eventMember ) );
        eventMemberResponse.setId( eventMember.getId() );
        eventMemberResponse.setFirstname( eventMember.getFirstname() );
        eventMemberResponse.setMiddlename( eventMember.getMiddlename() );
        eventMemberResponse.setLastname( eventMember.getLastname() );
        eventMemberResponse.setCompany( eventMember.getCompany() );
        eventMemberResponse.setPosition( eventMember.getPosition() );
        eventMemberResponse.setEmail( eventMember.getEmail() );
        eventMemberResponse.setPhone( eventMember.getPhone() );

        return eventMemberResponse;
    }

    private UUID eventMemberEventId(EventMember eventMember) {
        if ( eventMember == null ) {
            return null;
        }
        Event event = eventMember.getEvent();
        if ( event == null ) {
            return null;
        }
        UUID id = event.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
