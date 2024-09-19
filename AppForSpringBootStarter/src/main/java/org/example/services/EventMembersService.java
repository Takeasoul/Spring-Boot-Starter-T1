package org.example.services;

import org.example.web.DTO.request.EventMemberRequest;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventMemberResponse;
import org.example.web.DTO.response.EventResponse;

import java.util.UUID;

public interface EventMembersService extends  EntityService<EventMemberResponse, EventMemberRequest, UUID>{
}
