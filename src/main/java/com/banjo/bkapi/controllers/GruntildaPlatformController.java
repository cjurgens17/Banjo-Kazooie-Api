package com.banjo.bkapi.controllers;

import com.banjo.bkapi.models.GruntildaPlatform;
import com.banjo.bkapi.models.World;
import com.banjo.bkapi.services.GruntildaPlatformService;
import com.banjo.bkapi.services.WorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("gruntildaPlatform")
@CrossOrigin
public class GruntildaPlatformController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie gruntildaPlatform Request
            Endpoints:
             /gruntildaPlatform/{id}
             /gruntildaPlatform/{worldName}
             /gruntildaPlatform/all
     */

    private final GruntildaPlatformService gruntildaPlatformService;
    private final WorldService worldService;

    public GruntildaPlatformController(GruntildaPlatformService gruntildaPlatformService, WorldService worldService){
        this.gruntildaPlatformService = gruntildaPlatformService;
        this.worldService = worldService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GruntildaPlatform> getGruntildaPlatformById(@PathVariable Long id){
        return gruntildaPlatformService.getGruntildaPlatformById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{worldName}")
    public ResponseEntity<GruntildaPlatform> getGruntildaPlatformByWorld(@PathVariable String worldName){
        Optional<World> persistenceWorld = worldService.getWorldByName(worldName);
        if(persistenceWorld.isEmpty()) return ResponseEntity.notFound().build();

        World capturedWorld = persistenceWorld.get();

        GruntildaPlatform platform = capturedWorld.getGruntildaPlatform();

        return platform != null ? ResponseEntity.ok(platform) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<GruntildaPlatform>> getAllGruntildaPlatforms(){
        return gruntildaPlatformService.getAllGruntildaPlatforms()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
