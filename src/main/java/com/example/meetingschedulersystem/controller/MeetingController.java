package com.example.meetingschedulersystem.controller;

import com.example.meetingschedulersystem.model.Meeting;
import com.example.meetingschedulersystem.model.dto.MeetingRequest;
import com.example.meetingschedulersystem.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    @PostMapping
    public ResponseEntity<Meeting> scheduleMeeting(@RequestBody MeetingRequest request) {
       Meeting response =  meetingService.scheduleMeeting(request);
       return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{meeting-id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable("meeting-id") UUID id) {
        Meeting response =  meetingService.getMeetingById(id);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{meeting-id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable("meeting-id") UUID id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.ok().build();
    }

}
