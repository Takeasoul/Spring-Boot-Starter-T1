package org.example.repositories;

import org.example.entity.MethodsTimeTrack;
import org.example.web.DTO.response.MethodsTimeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MethodsTimeTrackRepository extends JpaRepository<MethodsTimeTrack, Long> {

    @Query("SELECT new org.example.web.DTO.response.MethodsTimeResponse(e.methodName, AVG(e.timeTaken)) " +
            "FROM MethodsTimeTrack e " +
            "GROUP BY e.methodName")
    List<MethodsTimeResponse> findAvgTimeByMethodName();

    @Query("SELECT new org.example.web.DTO.response.MethodsTimeResponse(e.methodName,  cast(max(e.timeTaken) as double)) " +
            "FROM MethodsTimeTrack e " +
            "GROUP BY e.methodName")
    List<MethodsTimeResponse> findMaxTimeByMethodName();
}