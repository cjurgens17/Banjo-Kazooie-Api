package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.DoorDTO;
import com.banjo.bkapi.dtos.HoneycombDTO;
import com.banjo.bkapi.dtos.JiggyDTO;
import com.banjo.bkapi.dtos.JiggyPadDTO;
import com.banjo.bkapi.models.*;
import com.banjo.bkapi.services.HubWorldService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hubWorld")
public class HubWorldController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Hubworld Request
            Endpoints:
             /hubWorld/id/{id}
             /hubWorld/doors/id/{id}
             /hubWord/honeycombs/id/{id}
             /hubWorld/jiggies/id/{id}
             /hubWorld/jiggypads/id/{id}
     */

    private final HubWorldService hubWorldService;

    public HubWorldController(HubWorldService hubWorldService){
        this.hubWorldService = hubWorldService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HubWorld> getHubWorldById(@PathVariable Long id){
        return hubWorldService.getHubworldById(id)
                .map(hubWorld -> ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                        .eTag(hubWorld.toString())
                        .body(hubWorld))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doors/id/{id}")
    public ResponseEntity<List<DoorDTO>> getHubWorldDoors(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

       if(optionalHubWorld.isPresent()){
           HubWorld hubWorld = optionalHubWorld.get();
           List<DoorDTO> doorDTOS = new ArrayList<>();

           for(Door door : hubWorld.getDoors()){
               DoorDTO doorDTO = new DoorDTO();
               doorDTO.setId(door.getId());
               doorDTO.setHub_world_id(door.getHubWorld().getId());
               doorDTO.setLocation(door.getLocation());
               doorDTO.setRequiredNotes(door.getRequiredNotes());
               doorDTOS.add(doorDTO);
           }
           return ResponseEntity.ok(doorDTOS);
       }else{
           return ResponseEntity.notFound().build();
       }
    }

    @GetMapping("/honeycombs/id/{id}")
    public ResponseEntity<List<HoneycombDTO>> getHubWorldHoneycombs(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        if(optionalHubWorld.isPresent()){
            HubWorld hubWorld = optionalHubWorld.get();
            List<HoneycombDTO> honeycombDTOS = new ArrayList<>();

            for(Honeycomb honeycomb : hubWorld.getHoneycombs()){
                HoneycombDTO honeycombDTO = new HoneycombDTO();
                honeycombDTO.setHub_world_id(honeycomb.getHubWorld().getId());
                honeycombDTO.setId(honeycomb.getId());
                honeycombDTO.setWorld_id(honeycomb.getWorld().getId());
                honeycombDTO.setLocation(honeycomb.getLocation());
                honeycombDTOS.add(honeycombDTO);
            }
            return ResponseEntity.ok(honeycombDTOS);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jiggies/id/{id}")
    public ResponseEntity<List<JiggyDTO>> getHubWorldJiggies(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        if(optionalHubWorld.isPresent()){
            HubWorld hubWorld = optionalHubWorld.get();
            List<JiggyDTO> jiggyDTOS = new ArrayList<>();

            for(Jiggy jiggy : hubWorld.getJiggies()){
                JiggyDTO jiggyDTO = new JiggyDTO();
                jiggyDTO.setWorld_id(jiggy.getWorld().getId());
                jiggyDTO.setLocation(jiggy.getLocation());
                jiggyDTO.setId(jiggy.getId());
                jiggyDTO.setHub_world_id(jiggy.getHubWorld().getId());
                jiggyDTOS.add(jiggyDTO);
            }
            return ResponseEntity.ok(jiggyDTOS);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jiggypads/id/{id}")
    public ResponseEntity<List<JiggyPadDTO>> getHubWorldJiggyPads(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        if(optionalHubWorld.isPresent()){
            HubWorld hubWorld = new HubWorld();
            List<JiggyPadDTO> jiggyPadDTOS = new ArrayList<>();

            for(JiggyPad jiggyPad : hubWorld.getJiggyPads()){
                JiggyPadDTO jiggyPadDTO = new JiggyPadDTO();
                jiggyPadDTO.setHub_world_id(jiggyPad.getHubWorld().getId());
                jiggyPadDTO.setWorld_id(jiggyPad.getWorld().getId());
                jiggyPadDTO.setLocation(jiggyPad.getLocation());
                jiggyPadDTO.setId(jiggyPad.getId());
                jiggyPadDTOS.add(jiggyPadDTO);
            }
            return ResponseEntity.ok(jiggyPadDTOS);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
