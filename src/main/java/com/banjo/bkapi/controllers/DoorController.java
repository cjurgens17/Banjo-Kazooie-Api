package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.DoorDTO;
import com.banjo.bkapi.models.Door;
import com.banjo.bkapi.services.DoorService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("door")
@CrossOrigin
public class DoorController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Door Request
            Endpoints:
             /door/id/{id}
     */

    private final DoorService doorService;

    public DoorController(DoorService doorService){
        this.doorService = doorService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoorDTO> getDoorById(@PathVariable Long id){
        Optional<Door> optDoor  = doorService.getDoorById(id);

        return optDoor.map(door -> {
            DoorDTO doorDTO = new DoorDTO();
            doorDTO.setLocation(door.getLocation());
            doorDTO.setHub_world_id(door.getHubWorld().getId());
            doorDTO.setRequiredNotes(door.getRequiredNotes());
            doorDTO.setId(door.getId());
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                    .eTag(door.getLocation())
                    .body(doorDTO);
        })
                .orElse(ResponseEntity.notFound().build());
    }
}
