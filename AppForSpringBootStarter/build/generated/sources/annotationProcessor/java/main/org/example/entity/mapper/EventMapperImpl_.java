package org.example.entity.mapper;

import javax.annotation.processing.Generated;
import org.example.entity.Event;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T19:19:31+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class EventMapperImpl_ implements EventMapper {

    @Override
    public Event requestToEvent(EventRequest request) {
        if ( request == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( request.getName() );
        event.setSummary( request.getSummary() );
        event.setDate( request.getDate() );
        event.setRegOpen( request.getRegOpen() );
        event.setAddress( request.getAddress() );
        event.setStartRegistrationDate( request.getStartRegistrationDate() );
        event.setCloseRegistrationDate( request.getCloseRegistrationDate() );

        return event;
    }

    @Override
    public EventResponse eventToResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponse eventResponse = new EventResponse();

        eventResponse.setId( event.getId() );
        eventResponse.setName( event.getName() );
        eventResponse.setSummary( event.getSummary() );
        eventResponse.setDate( event.getDate() );
        eventResponse.setRegOpen( event.getRegOpen() );
        eventResponse.setAddress( event.getAddress() );
        eventResponse.setStartRegistrationDate( event.getStartRegistrationDate() );
        eventResponse.setCloseRegistrationDate( event.getCloseRegistrationDate() );

        return eventResponse;
    }
}
