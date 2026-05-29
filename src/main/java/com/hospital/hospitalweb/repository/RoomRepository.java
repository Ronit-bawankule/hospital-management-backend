package com.hospital.hospitalweb.repository;

import com.hospital.hospitalweb.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository
        extends JpaRepository<Room, Long> {
}