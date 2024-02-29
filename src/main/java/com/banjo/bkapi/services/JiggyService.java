package com.banjo.bkapi.services;


import com.banjo.bkapi.models.Honeycomb;
import com.banjo.bkapi.models.Jiggy;
import com.banjo.bkapi.repositories.JiggyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JiggyService {

    private final JiggyRepository jiggyRepository;

    public JiggyService(JiggyRepository jiggyRepository){
        this.jiggyRepository = jiggyRepository;
    }

    public Optional<Jiggy> findById(Long id){
        return jiggyRepository.findById(id);
    }

    public Optional<List<Jiggy>> getAllJiggies(){
        return Optional.of(jiggyRepository.findAll());
    }

    public Optional<List<Jiggy>> findJiggiesByWorld(Long id){
        return Optional.of(jiggyRepository.findAll().stream()
                .filter(jiggy -> Objects.equals(jiggy.getWorld().getId(), id))
                .collect(Collectors.toList()));
    }
}
