package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.HoneycombDTO;
import com.banjo.bkapi.models.Honeycomb;
import com.banjo.bkapi.services.HoneycombService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                    .eTag(honeycomb.getLocation())
                    .body(honeycombDTO);
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoneycombDTO>> getAllHoneycombs(){
        Optional<List<Honeycomb>> optHoneycombs = honeycombService.getAllHoneycombs();

        if (optHoneycombs.isPresent()) {
            List<Honeycomb> honeycombs = optHoneycombs.get();
            List<HoneycombDTO> honeycombDTOS = new ArrayList<>();

            for (Honeycomb honeycomb : honeycombs) {
                HoneycombDTO honeycombDTO = new HoneycombDTO();
                honeycombDTO.setId(honeycomb.getId());
                honeycombDTO.setLocation(honeycomb.getLocation());
                honeycombDTO.setWorld_id(honeycomb.getWorld().getId());
                honeycombDTO.setHub_world_id(honeycomb.getHubWorld().getId());
                honeycombDTOS.add(honeycombDTO);
            }
            return ResponseEntity.ok(honeycombDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<List<HoneycombDTO>> getHoneycombByWorld(@PathVariable Long id){
        Optional<List<Honeycomb>> optHoneycombs = honeycombService.findHoneycombsByWorld(id);

        if(optHoneycombs.isPresent()){
            List<Honeycomb> honeycombs = optHoneycombs.get();
            List<HoneycombDTO> honeycombDTOS = new ArrayList<>();

            for(Honeycomb honeycomb : honeycombs){
                HoneycombDTO  honeycombDTO = new HoneycombDTO();
                honeycombDTO.setId(honeycomb.getId());
                honeycombDTO.setLocation(honeycomb.getLocation());
                honeycombDTO.setWorld_id(honeycomb.getWorld().getId());
                honeycombDTO.setHub_world_id(honeycomb.getHubWorld().getId());
                honeycombDTOS.add(honeycombDTO);
            }
            return ResponseEntity.ok(honeycombDTOS);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
