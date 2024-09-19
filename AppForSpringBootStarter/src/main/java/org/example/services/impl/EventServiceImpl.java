package org.example.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aop.annotation.Logging;
import org.example.aop.annotation.TrackAsyncTime;
import org.example.aop.annotation.TrackTime;
import org.example.entity.Event;
import org.example.entity.EventMember;
import org.example.entity.mapper.EventMapper;
import org.example.repositories.EventRepository;
import org.example.services.EventService;
import org.example.web.DTO.request.EventMemberRequest;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventMemberResponse;
import org.example.web.DTO.response.EventResponse;
import org.example.web.DTO.response.ModelListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;

    private final EventRepository repository;

    @Override
    @Transactional
    @Async
    public CompletableFuture<ModelListResponse<EventResponse>> findAll(Pageable pageable) {
        log.info("Find all events");
        Page<Event> events = repository.findAll(pageable);
        return CompletableFuture.completedFuture(ModelListResponse.<EventResponse>builder()
                .totalCount(events.getTotalElements())
                .data(events.stream().map(eventMapper::eventToResponse).toList())
                .build());

    }

    @Override
    @Transactional
    public EventResponse findById(UUID id) {
        log.info("Find event with ID: {}", id);
        return eventMapper.eventToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Event with ID {0} not found!", id)
                )));
    }


    @Override
    @Transactional
    public EventResponse create(EventRequest entityRequest) {
        log.info("Create event {}",entityRequest);
        return eventMapper.eventToResponse(
                repository.save(eventMapper.requestToEvent(entityRequest))
        );
    }

    @Override
    @Transactional
    public EventResponse update(UUID uuid, EventRequest entityRequest) {
        Event event = eventMapper.requestToEvent(entityRequest);
        repository.save(event);
        return eventMapper.eventToResponse(event);
    }



    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.info("Delete event with ID: {}", id);
        repository.deleteById(id);
    }
}
