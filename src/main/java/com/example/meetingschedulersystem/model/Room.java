package com.example.meetingschedulersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "room")
public class Room extends BaseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int capacity;
    @OneToMany(mappedBy = "room")
    private List<Meeting> meetings;
}
