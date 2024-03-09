package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.models.BKCharacter;
import com.banjo.bkapi.services.BKCharacterService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("BKCharacter")
@CrossOrigin
public class BKCharacterController {
    /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Character Request
            Endpoints:
             /BKCharacter/name/{name}
             /BKCharacter/id/{id}
             /BKCharacter/description/{id}
     */
    private final BKCharacterService bkCharacterService;

    public BKCharacterController(BKCharacterService bkCharacterService){
        this.bkCharacterService = bkCharacterService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BKCharacter> getBKCharacterByName(@PathVariable String name){
        Optional<BKCharacter> optionalBKCharacter = bkCharacterService.findCharacterByName(name);

        return optionalBKCharacter.map(bkCharacter -> ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                        .eTag(bkCharacter.getName())
                        .body(bkCharacter))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BKCharacter> getBKCharacterById(@PathVariable Long id){
        Optional<BKCharacter> optionalBKCharacter = bkCharacterService.findCharacterById(id);

        return optionalBKCharacter.map(bkCharacter -> ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                        .eTag(bkCharacter.getName())
                        .body(bkCharacter))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/description/{id}")
    public ResponseEntity<String> getBKCharacterDescription(@PathVariable Long id){
        Optional<BKCharacter> optionalBKCharacter = bkCharacterService.findCharacterById(id);

        return optionalBKCharacter.map(BKCharacter::getDescription)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
