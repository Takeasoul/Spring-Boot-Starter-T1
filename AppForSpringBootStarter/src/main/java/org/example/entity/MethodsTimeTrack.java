package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(schema = "public", name = "methods_time")
public class MethodsTimeTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "name", nullable = false)
    private String methodName;

    @Column(name = "time", nullable = false)
    private Long timeTaken;
}
