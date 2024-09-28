package com.example.meetingschedulersystem.service;

import com.example.meetingschedulersystem.model.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    Room addRoom(Room room);
    Room updateRoom(Room room, UUID id);
    void deleteRoom(UUID id);
    Room getRoomById(UUID id);
    List<Room> getAllRooms();
}
