package org.example.entity.mapper;

import javax.annotation.processing.Generated;
import org.example.entity.EventMember;
import org.example.web.DTO.response.EventMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T19:19:31+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
@Primary
public class EventMemberMapperImpl extends EventMemberMapperDelegate {

    @Autowired
    @Qualifier("delegate")
    private EventMemberMapper delegate;

    @Override
    public EventMemberResponse eventMemberToResponse(EventMember eventMember)  {
        return delegate.eventMemberToResponse( eventMember );
    }
}
