package com.example.meetingschedulersystem.controller;

import com.example.meetingschedulersystem.model.Room;
import com.example.meetingschedulersystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room response = roomService.addRoom(room);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{room-id}")
    public ResponseEntity<Room> getRoom(@PathVariable(name = "room-id") UUID roomId) {
        Room response = roomService.getRoomById(roomId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Room>> getRooms() {
        List<Room> response = roomService.getAllRooms();
        return new ResponseEntity<>(response, response.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK);
    }
    @DeleteMapping("/{room-id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable(name = "room-id") UUID roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{room-id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable(name = "room-id") UUID roomId) {
        Room response = roomService.updateRoom(room, roomId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
