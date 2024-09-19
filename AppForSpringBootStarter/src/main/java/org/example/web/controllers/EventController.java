package org.example.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.Event;
import org.example.services.EventService;
import org.example.web.DTO.request.EventRequest;
import org.example.web.DTO.response.EventResponse;
import org.example.web.DTO.response.ModelListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Tag(name = "Events", description = "Мероприятия")
@RequestMapping(path="api/v1/events",produces = "application/json")
@CrossOrigin()
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                eventService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.create(request));
    }

    @GetMapping
    public ResponseEntity<ModelListResponse<EventResponse>> getAllEvents(Pageable pageable) {
        try {
            return ResponseEntity.ok(eventService.findAll(pageable).get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
    }}

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable UUID id, @RequestBody EventRequest request) {
        return ResponseEntity.ok(eventService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}