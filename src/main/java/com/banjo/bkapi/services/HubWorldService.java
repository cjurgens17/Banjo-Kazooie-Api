package com.banjo.bkapi.services;


import com.banjo.bkapi.models.HubWorld;
import com.banjo.bkapi.repositories.HubWorldRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HubWorldService {

    private final HubWorldRepository hubWorldRepository;

    public HubWorldService(HubWorldRepository hubWorldRepository){
        this.hubWorldRepository = hubWorldRepository;
    }

    public Optional<HubWorld> getHubworldById(Long id){
        return hubWorldRepository.findById(id);
    }


}
