package com.banjo.bkapi.controllers;

import com.banjo.bkapi.models.World;
import com.banjo.bkapi.services.WorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("world")
@CrossOrigin
public class WorldController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie World Request
            Endpoints:
             /world/id/{id}
             /world/all
     */

    private final WorldService worldService;

    public WorldController(WorldService worldService){
        this.worldService = worldService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<World> getWorldById(@PathVariable Long id){
        return worldService.findWorldById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<World>> getAllWorlds(){
        return worldService.getAllWorlds().map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
