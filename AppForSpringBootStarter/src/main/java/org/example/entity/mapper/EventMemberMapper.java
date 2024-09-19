package org.example.entity.mapper;


import org.example.entity.EventMember;
import org.example.web.DTO.request.EventMemberRequest;
import org.example.web.DTO.response.EventMemberResponse;
import org.mapstruct.*;

@DecoratedWith(EventMemberMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EventMemberMapper {
    EventMember requestToEventMember(EventMemberRequest request);
    @Mapping(source = "event.id", target = "eventId")
    EventMemberResponse eventMemberToResponse(EventMember eventMember);

}
