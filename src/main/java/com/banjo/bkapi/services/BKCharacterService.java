package com.banjo.bkapi.services;


import com.banjo.bkapi.models.BKCharacter;
import com.banjo.bkapi.repositories.BKCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BKCharacterService {

    private final BKCharacterRepository BKCharacterRepository;

    public BKCharacterService(BKCharacterRepository BKCharacterRepository){
        this.BKCharacterRepository = BKCharacterRepository;
    }

    public Optional<BKCharacter> findCharacterById(Long id){
        return BKCharacterRepository.findById(id);
    }

    public Optional<BKCharacter> findCharacterByName(String name){
      return BKCharacterRepository.findByNameEquals(name);
    }

}
