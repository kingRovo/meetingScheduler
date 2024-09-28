package com.example.meetingschedulersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "meeting")
public class Meeting extends BaseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private long startTime;
    private long endTime;
    @ManyToOne
    private User organizer;
    @ManyToMany
    private List<User> attendees;
    @ManyToOne
    private Room room;
}
