package com.banjo.bkapi.services;

import com.banjo.bkapi.models.GruntildaPlatform;
import com.banjo.bkapi.repositories.GruntildaPlatformRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GruntildaPlatformService {

    private final GruntildaPlatformRepository gruntildaPlatformRepository;

    public GruntildaPlatformService(GruntildaPlatformRepository gruntildaPlatformRepository){
        this.gruntildaPlatformRepository = gruntildaPlatformRepository;
    }

    public Optional<GruntildaPlatform> getGruntildaPlatformById(Long id){
        return gruntildaPlatformRepository.findById(id);
    }

    public Optional<List<GruntildaPlatform>> getAllGruntildaPlatforms(){
        return Optional.of(gruntildaPlatformRepository.findAll());
    }
}
