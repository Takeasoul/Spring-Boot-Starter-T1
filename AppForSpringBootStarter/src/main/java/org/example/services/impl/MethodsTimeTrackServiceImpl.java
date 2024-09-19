package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.MethodsTimeTrack;
import org.example.repositories.MethodsTimeTrackRepository;
import org.example.services.MethodsTimeTrackService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MethodsTimeTrackServiceImpl implements MethodsTimeTrackService {
    private final MethodsTimeTrackRepository repository;

    @Async
    @Transactional
    public void saveExecutionTime(String methodName, Long timeTaken) {
        log.info("Saving execution time for method: {}", methodName);
        repository.save(MethodsTimeTrack.builder()
                .methodName(methodName)
                .createdAt(ZonedDateTime.now())
                .timeTaken(timeTaken)
                .build());
    }

}

