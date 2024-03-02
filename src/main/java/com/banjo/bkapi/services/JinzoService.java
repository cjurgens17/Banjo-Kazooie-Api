package com.banjo.bkapi.services;


import com.banjo.bkapi.enums.Color;
import com.banjo.bkapi.models.Jinzo;
import com.banjo.bkapi.repositories.JinzoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class JinzoService {

    private final JinzoRepository jinzoRepository;

    public JinzoService(JinzoRepository jinzoRepository){
        this.jinzoRepository = jinzoRepository;
    }

    public Optional<List<Jinzo>> findByColor(Color color){
        List<Jinzo> filteredJinzo = jinzoRepository.findAll().stream()
                .filter(jinzo -> jinzo.getColor() == color).toList();
        return Optional.of(filteredJinzo);
    }

    public Optional<Jinzo> findById(Long id){
        return jinzoRepository.findById(id);
    }

    public Optional<List<Jinzo>> findByWorldId(Long id){
        List<Jinzo> filteredByWorld = jinzoRepository.findAll().stream()
                .filter(jinzo -> Objects.equals(jinzo.getWorld().getId(), id))
                .toList();
        return Optional.of(filteredByWorld);
    }

    public Optional<List<Jinzo>> findAllJinzos(){
        return Optional.of(jinzoRepository.findAll());
    }


}
