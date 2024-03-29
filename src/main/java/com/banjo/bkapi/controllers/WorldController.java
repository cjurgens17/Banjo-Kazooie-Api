package com.banjo.bkapi.controllers;

import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.models.Jinzo;
import com.banjo.bkapi.models.World;
import com.banjo.bkapi.services.HoneycombService;
import com.banjo.bkapi.services.JiggyService;
import com.banjo.bkapi.services.JinzoService;
import com.banjo.bkapi.services.WorldService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return worldService.findWorldById(id)
                .map(world -> ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(CacheTimes.GROUPED_ENTITY,CacheTimes.DAYS))
                        .eTag(world.getName())
                        .body(world))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<World>> getAllWorlds(){
        return worldService.getAllWorlds().map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
