package com.csc3402.lab.avr.repository;

import com.csc3402.lab.avr.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findByRoomType(String selectedRoom);
}