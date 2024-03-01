package com.banjo.bkapi.services;

import com.banjo.bkapi.models.Door;
import com.banjo.bkapi.repositories.DoorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoorService {

    private final DoorRepository doorRepository;

    public DoorService(DoorRepository doorRepository){
        this.doorRepository = doorRepository;
    }

    public Optional<Door> getDoorById(Long id){
        return doorRepository.findById(id);
    }
}
