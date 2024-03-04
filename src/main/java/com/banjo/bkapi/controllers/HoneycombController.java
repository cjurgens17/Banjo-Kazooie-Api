package com.banjo.bkapi.controllers;


import com.banjo.bkapi.dtos.HoneycombDTO;
import com.banjo.bkapi.models.Honeycomb;
import com.banjo.bkapi.services.HoneycombService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("honeycomb")
@CrossOrigin
public class HoneycombController {

     /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Honeycomb Request
            Endpoints:
             /honeycomb/id/{id}
             /honeycomb/world/{id}
             /honeycomb/all
     */

    private final HoneycombService honeycombService;

    public HoneycombController(HoneycombService honeycombService){
        this.honeycombService = honeycombService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<HoneycombDTO> getHoneycombById(@PathVariable Long id){
        Optional<Honeycomb> optHoneycomb = honeycombService.getHoneycombById(id);

        return optHoneycomb.map(honeycomb -> {
            HoneycombDTO honeycombDTO = new HoneycombDTO();
            honeycombDTO.setWorld_id(honeycomb.getWorld().getId());
            honeycombDTO.setHub_world_id(honeycomb.getHubWorld().getId());
            honeycombDTO.setLocation(honeycomb.getLocation());
            honeycombDTO.setId(honeycomb.getId());
            return ResponseEntity.ok(honeycombDTO);
        })
                .orElse(ResponseEntity.notFound().build());
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
