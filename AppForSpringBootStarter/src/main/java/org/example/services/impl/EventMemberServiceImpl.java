package org.example.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aop.annotation.Logging;
import org.example.aop.annotation.TrackAsyncTime;
import org.example.aop.annotation.TrackTime;
import org.example.entity.EventMember;
import org.example.entity.mapper.EventMemberMapper;
import org.example.repositories.EventMemberRepository;
import org.example.services.EventMembersService;
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
public class EventMemberServiceImpl implements EventMembersService {

    private final EventMemberMapper eventMemberMapper;
    private final EventMemberRepository repository;

    @Override
    @Async
    public CompletableFuture<ModelListResponse<EventMemberResponse>> findAll(Pageable pageable) {
        log.info("Find all event members");
        Page<EventMember> eventMembers = repository.findAll(pageable);
        return CompletableFuture.completedFuture(ModelListResponse.<EventMemberResponse>builder()
                .totalCount(eventMembers.getTotalElements())
                .data(eventMembers.stream().map(eventMemberMapper::eventMemberToResponse).toList())
                .build());
    }

    @Override
    @Transactional
    public EventMemberResponse findById(UUID id) {
        log.info("Find event member with ID: {}", id);
        return eventMemberMapper.eventMemberToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Event member with ID {0} not found!", id)
                )));
    }

    @Override
    public EventMemberResponse create(EventMemberRequest entityRequest) {
        log.info("Create event member {}",entityRequest);
        EventMember eventMember = eventMemberMapper.requestToEventMember(entityRequest);
        if (!eventMember.getEvent().getRegOpen()) {
            throw new EntityNotFoundException(
                    MessageFormat.format("Registration on event {0} closed!",eventMember.getEvent().getName())
            );
        }
        return eventMemberMapper.eventMemberToResponse(
                repository.save(eventMemberMapper.requestToEventMember(entityRequest))
        );
    }


    @Override
    public void deleteById(UUID id) {
        log.info("Delete event member with ID: {}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public EventMemberResponse update(UUID uuid, EventMemberRequest entityRequest) {
        EventMember member = eventMemberMapper.requestToEventMember(entityRequest);
        repository.save(member);
        return eventMemberMapper.eventMemberToResponse(member);
    }
}
