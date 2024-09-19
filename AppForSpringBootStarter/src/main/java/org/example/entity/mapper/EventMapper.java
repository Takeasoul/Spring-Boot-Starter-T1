package org.example.entity.mapper;


import org.example.entity.Event;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(EventMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EventMapper {
    Event requestToEvent(EventRequest request);


    EventResponse eventToResponse(Event event);
}
