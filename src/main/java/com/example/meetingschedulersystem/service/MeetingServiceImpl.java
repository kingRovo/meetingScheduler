package com.example.meetingschedulersystem.service;

import com.example.meetingschedulersystem.model.Meeting;
import com.example.meetingschedulersystem.model.Room;
import com.example.meetingschedulersystem.model.User;
import com.example.meetingschedulersystem.model.dto.MeetingRequest;
import com.example.meetingschedulersystem.repository.MeetingRepository;
import com.example.meetingschedulersystem.repository.RoomRepository;
import com.example.meetingschedulersystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MeetingServiceImpl implements MeetingService {


    private final MeetingRepository meetingRepository;

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public boolean checkCollision(long start, long end, List<Meeting> calendar) {
        return calendar.stream().anyMatch(meeting ->
                (start > meeting.getEndTime()) && end < (meeting.getStartTime()));
    }

    @Transactional
    @Override
    public Meeting scheduleMeeting(MeetingRequest request) {
        User organizer = userRepository.findById(request.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        List<User> attendees = userRepository.findAllById(request.getAttendeeIds());
        if (attendees.size() != request.getAttendeeIds().size()) {
            throw new RuntimeException("One or more attendees not found");
        }

        Room room = null;
        if (request.getRoomId() != null) {
            room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
        }

        if (checkCollision(request.getStartTime(), request.getEndTime(), organizer.getMeetings())) {
            throw new RuntimeException("Collision detected for organizer");
        }

        for (User attendee : attendees) {
            if (checkCollision(request.getStartTime(), request.getEndTime(), attendee.getMeetings())) {
                throw new RuntimeException("Collision detected for attendee: " + attendee.getName());
            }
        }

        if (room != null && checkCollision(request.getStartTime(), request.getEndTime(), room.getMeetings())) {
            throw new RuntimeException("Room is not available at the specified time");
        }

        Meeting meeting = new Meeting();
        meeting.setTitle(request.getTitle());
        meeting.setStartTime(request.getStartTime());
        meeting.setEndTime(request.getEndTime());
        meeting.setOrganizer(organizer);
        meeting.setAttendees(attendees);
        meeting.setRoom(room);

        return meetingRepository.save(meeting);
    }

    @Override
    public Meeting getMeetingById(UUID id) {
        return meetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Meeting not found"));
    }

    @Override
    public void deleteMeeting(UUID id) {
        Meeting meeting = getMeetingById(id);
        // We may have checks here like meeting status or meeting is deletable or not
        meetingRepository.delete(meeting);
    }
}
