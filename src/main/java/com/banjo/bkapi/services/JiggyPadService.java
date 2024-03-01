package com.banjo.bkapi.services;


import com.banjo.bkapi.models.JiggyPad;
import com.banjo.bkapi.repositories.JiggyPadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JiggyPadService {

    private final JiggyPadRepository jiggyPadRepository;

    public JiggyPadService(JiggyPadRepository jiggyPadRepository){
        this.jiggyPadRepository = jiggyPadRepository;
    }

    public Optional<JiggyPad> getJiggyPadById(Long id){
        return jiggyPadRepository.findById(id);
    }

    public Optional<List<JiggyPad>> getAllJiggyPads(){
        return Optional.of(jiggyPadRepository.findAll());
    }
}
