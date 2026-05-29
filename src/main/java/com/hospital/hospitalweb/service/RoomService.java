package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Room;
import com.hospital.hospitalweb.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(
            RoomRepository roomRepository
    ) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(
            Room room
    ) {

        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {

        return roomRepository.findAll();
    }

    public void deleteRoom(
            Long id
    ) {

        roomRepository.deleteById(id);
    }
}