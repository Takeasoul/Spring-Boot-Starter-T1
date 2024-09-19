package org.example.services;

import org.example.web.DTO.response.MethodsTimeResponse;

import java.util.List;

public interface MethodsStatisticsService {

    List<MethodsTimeResponse> getAvgExecutionTimeMethods();

    List<MethodsTimeResponse> getMaxExecutionTimeMethods();
}
