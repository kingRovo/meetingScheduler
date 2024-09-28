package com.example.meetingschedulersystem.service;

import com.example.meetingschedulersystem.model.Meeting;
import com.example.meetingschedulersystem.model.dto.MeetingRequest;

import java.util.UUID;

public interface MeetingService {
    Meeting scheduleMeeting(MeetingRequest request);
    Meeting getMeetingById(UUID id);
    void deleteMeeting(UUID id);
}
