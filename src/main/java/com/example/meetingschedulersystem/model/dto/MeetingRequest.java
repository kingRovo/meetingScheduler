package com.example.meetingschedulersystem.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MeetingRequest {
    private String title;
    private long startTime;
    private long endTime;
    private UUID organizerId;
    private List<UUID> attendeeIds;
    private UUID roomId;
}
