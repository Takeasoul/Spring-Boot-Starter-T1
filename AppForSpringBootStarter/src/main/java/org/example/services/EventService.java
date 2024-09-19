package org.example.services;

import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventResponse;

import java.util.UUID;

public interface EventService extends EntityService<EventResponse, EventRequest, UUID> {
}
