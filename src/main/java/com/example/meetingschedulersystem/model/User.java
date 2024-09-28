package com.example.meetingschedulersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "user")
public class User extends BaseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "organizer")
    private List<Meeting> meetings;
}
