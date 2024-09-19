package org.example.web.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMemberResponse {

    private UUID id;

    private String firstname;

    private String middlename;

    private String lastname;

    private String company;

    private String position;

    private String email;

    private String phone;

    private UUID eventId;

}