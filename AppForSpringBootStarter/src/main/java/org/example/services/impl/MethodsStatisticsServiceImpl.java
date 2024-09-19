package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.repositories.MethodsTimeTrackRepository;
import org.example.services.MethodsStatisticsService;
import org.example.web.DTO.response.MethodsTimeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MethodsStatisticsServiceImpl implements MethodsStatisticsService {

    private final MethodsTimeTrackRepository repository;

    @Transactional
    public List<MethodsTimeResponse> getAvgExecutionTimeMethods() {
        log.info("Getting avg execution time methods...");
        return repository.findAvgTimeByMethodName();
    }

    @Transactional
    public List<MethodsTimeResponse> getMaxExecutionTimeMethods() {
        log.info("Getting max execution time methods...");
        return repository.findMaxTimeByMethodName();
    }
}