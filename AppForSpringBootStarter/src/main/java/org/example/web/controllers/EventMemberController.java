package org.example.web.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.services.EventMembersService;
import org.example.web.DTO.request.EventMemberRequest;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventMemberResponse;
import org.example.web.DTO.response.EventResponse;
import org.example.web.DTO.response.ModelListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@RestController
@RequiredArgsConstructor
@Tag(name = "Events-members", description = "Участники")
@RequestMapping(path="api/v1/event-members",produces = "application/json")
@CrossOrigin()
public class EventMemberController {

    private final EventMembersService eventMemberService;

    @GetMapping("/{id}")
    public ResponseEntity<EventMemberResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                eventMemberService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<EventMemberResponse> createEventMember(@RequestBody EventMemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventMemberService.create(request));
    }

    @GetMapping
    public ResponseEntity<ModelListResponse<EventMemberResponse>> getAllEventMembers(Pageable pageable) {
        try {
            return ResponseEntity.ok(eventMemberService.findAll(pageable).get());
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventMemberResponse> updateEvent(@PathVariable UUID id, @RequestBody EventMemberRequest request) {
        return ResponseEntity.ok(eventMemberService.update(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventMember(@PathVariable UUID id) {
        eventMemberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


