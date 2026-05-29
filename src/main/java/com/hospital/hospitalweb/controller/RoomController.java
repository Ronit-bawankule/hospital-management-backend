package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Room;
import com.hospital.hospitalweb.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
public class RoomController {

    private final RoomService roomService;

    public RoomController(
            RoomService roomService
    ) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(
            @RequestBody Room room
    ) {

        return roomService.addRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {

        return roomService.getAllRooms();
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(
            @PathVariable Long id
    ) {

        roomService.deleteRoom(id);
    }
}