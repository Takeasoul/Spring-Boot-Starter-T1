package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@Entity
@Table(name = "error_log")
@FieldNameConstants
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "error_message", length = 2000)
    private String errorMessage;

    @NotNull
    @Column(name = "error_class")
    private String errorClass;

    @NotNull
    @Column(name = "error_count")
    private int errorCount;
}