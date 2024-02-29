package com.banjo.bkapi.services;

import com.banjo.bkapi.models.Honeycomb;
import com.banjo.bkapi.repositories.HoneycombRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HoneycombService {

    private final HoneycombRepository honeycombRepository;

    public HoneycombService(HoneycombRepository honeycombRepository){
        this.honeycombRepository = honeycombRepository;
    }

    public Optional<Honeycomb> getHoneycombById(Long id){
        return honeycombRepository.findById(id);
    }

    public Optional<List<Honeycomb>> getAllHoneycombs(){
        return Optional.of(honeycombRepository.findAll());
    }

    public Optional<List<Honeycomb>> findHoneycombsByWorld(Long id){
        return Optional.of(honeycombRepository.findAll().stream()
                .filter(honeycomb -> Objects.equals(honeycomb.getWorld().getId(), id))
                .collect(Collectors.toList()));
    }
}
