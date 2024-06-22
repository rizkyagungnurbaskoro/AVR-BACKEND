package com.csc3402.lab.avr.service;

import com.csc3402.lab.avr.model.Room;
import com.csc3402.lab.avr.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> listAllRooms(){
        return roomRepository.findAll();
    }

}