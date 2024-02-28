package com.banjo.bkapi.services;

import com.banjo.bkapi.models.World;
import com.banjo.bkapi.repositories.WorldRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorldService {

    private final WorldRepository worldRepository;

    public WorldService(WorldRepository worldRepository){
        this.worldRepository = worldRepository;
    }

    public Optional<World> findWorldById(Long id){
        return worldRepository.findById(id);
    }

    public Optional<List<World>> getAllWorlds(){
        //sorted by id
        return Optional.of(worldRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }
}
