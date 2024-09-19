package org.example.web.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotNull
    private String name;

    private String summary;

    @NotNull
    private Instant date;

    @NotNull
    private Boolean regOpen;

    @NotNull
    private String address;

    @NotNull
    private Instant startRegistrationDate;

    @NotNull
    private Instant closeRegistrationDate;
}