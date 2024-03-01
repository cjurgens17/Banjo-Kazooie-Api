package com.banjo.bkapi.controllers;


import com.banjo.bkapi.models.Honeycomb;
import com.banjo.bkapi.services.HoneycombService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("honeycomb")
@CrossOrigin
public class HoneycombController {

     /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Honeycomb Request
            Endpoints:
             /honeycomb/{id}
             /honeycomb/world/{id}
             /honeycomb/all
     */

    private final HoneycombService honeycombService;

    public HoneycombController(HoneycombService honeycombService){
        this.honeycombService = honeycombService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Honeycomb> getHoneycombById(@PathVariable Long id){
        return honeycombService.getHoneycombById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Honeycomb>> getAllHoneycombs(){
        return honeycombService.getAllHoneycombs()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<List<Honeycomb>> getHoneycombByWorld(@PathVariable Long id){
        return honeycombService.findHoneycombsByWorld(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
