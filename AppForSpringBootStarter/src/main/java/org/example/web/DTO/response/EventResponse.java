package org.example.web.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private UUID id;

    private String name;

    private String summary;

    private Instant date;

    private Boolean regOpen;

    private String address;

    private Instant startRegistrationDate;

    private Instant closeRegistrationDate;

}