package org.example.web.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.ErrorLog;
import org.example.services.ErrorLogService;
import org.example.services.MethodsStatisticsService;
import org.example.web.DTO.response.MethodsTimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ExecutionTimeStatistic", description = "Статистика времени выполнения методов")
@RequestMapping("api/v1/statistics")
public class MethodsStatisticController {

        private final MethodsStatisticsService statisticsService;

        private final ErrorLogService errorLogService;

        @Operation(summary = "Получить среднее время выполнения всех методов помеченными аннотациями @TrackTime и @TrackAsyncTime")
        @GetMapping("/average-execution-time-methods")
        public ResponseEntity<List<MethodsTimeResponse>> getAvgStatistics() {
            return ResponseEntity.ok(statisticsService.getAvgExecutionTimeMethods());
        }

        @Operation(summary = "Получить максимальное время выполнения всех методов помеченными аннотациями @TrackTime и @TrackAsyncTime")
        @GetMapping("/max-execution-time-methods")
        public ResponseEntity<List<MethodsTimeResponse>> getMaxStatistics() {
            return ResponseEntity.ok(statisticsService.getMaxExecutionTimeMethods());
        }

        @Operation(summary = "Получить методы и количество ошибок, возникших в данных методах")
        @GetMapping("/get-error-log")
        public ResponseEntity<List<ErrorLog>> getErrorLog() {
            return ResponseEntity.ok(errorLogService.getErrorLog());
        }


    }
