package com.example.meetingschedulersystem.service;

import com.example.meetingschedulersystem.model.Room;
import com.example.meetingschedulersystem.repository.RoomRepository;
import com.example.meetingschedulersystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room room, UUID roomId) {
        getRoomById(roomId);
        room.setId(roomId);
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(UUID id) {
         getRoomById(id);
         roomRepository.deleteById(id);
    }

    @Override
    public Room getRoomById(UUID id) {
        return roomRepository.findById(id).orElseThrow(()->new RuntimeException("Room not found"));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
