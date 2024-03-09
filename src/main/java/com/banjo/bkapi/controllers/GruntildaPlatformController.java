package com.banjo.bkapi.controllers;

import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.GruntildaPlatformDTO;
import com.banjo.bkapi.models.GruntildaPlatform;
import com.banjo.bkapi.models.World;
import com.banjo.bkapi.services.GruntildaPlatformService;
import com.banjo.bkapi.services.WorldService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("gruntildaPlatform")
@CrossOrigin
public class GruntildaPlatformController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie gruntildaPlatform Request
            Endpoints:
             /gruntildaPlatform/id/{id}
             /gruntildaPlatform/world/{worldName}
             /gruntildaPlatform/all
     */

    private final GruntildaPlatformService gruntildaPlatformService;
    private final WorldService worldService;

    public GruntildaPlatformController(GruntildaPlatformService gruntildaPlatformService, WorldService worldService){
        this.gruntildaPlatformService = gruntildaPlatformService;
        this.worldService = worldService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GruntildaPlatformDTO> getGruntildaPlatformById(@PathVariable Long id){
        Optional<GruntildaPlatform> optGruntildaPlatform = gruntildaPlatformService.getGruntildaPlatformById(id);

        return optGruntildaPlatform.map(gruntildaPlatform -> {
            GruntildaPlatformDTO gruntildaPlatformDTO = new GruntildaPlatformDTO();
            gruntildaPlatformDTO.setLocation(gruntildaPlatform.getLocation());
            gruntildaPlatformDTO.setWorld_id(gruntildaPlatform.getWorld().getId());
            gruntildaPlatformDTO.setId(gruntildaPlatform.getId());
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                    .eTag(gruntildaPlatform.getLocation())
                    .body(gruntildaPlatformDTO);
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/world/{worldName}")
    public ResponseEntity<GruntildaPlatformDTO> getGruntildaPlatformByWorld(@PathVariable String worldName){
        Optional<World> persistenceWorld = worldService.getWorldByName(worldName);

        return persistenceWorld.map(world -> {
            GruntildaPlatformDTO gruntildaPlatformDTO = new GruntildaPlatformDTO();
            gruntildaPlatformDTO.setId(world.getGruntildaPlatform().getId());
            gruntildaPlatformDTO.setLocation(world.getGruntildaPlatform().getLocation());
            gruntildaPlatformDTO.setWorld_id(world.getId());
            return

                    ResponseEntity.ok(gruntildaPlatformDTO);
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<GruntildaPlatformDTO>> getAllGruntildaPlatforms() {
        Optional<List<GruntildaPlatform>> optGruntildaPlatforms = gruntildaPlatformService.getAllGruntildaPlatforms();

        if (optGruntildaPlatforms.isPresent()) {
            List<GruntildaPlatform> gruntildaPlatforms = optGruntildaPlatforms.get();
            List<GruntildaPlatformDTO> gruntildaPlatformDTOS = new ArrayList<>();

            for (GruntildaPlatform platform : gruntildaPlatforms) {
                GruntildaPlatformDTO gruntildaPlatformDTO = new GruntildaPlatformDTO();
                gruntildaPlatformDTO.setId(platform.getId());
                gruntildaPlatformDTO.setLocation(platform.getLocation());
                gruntildaPlatformDTO.setWorld_id(platform.getWorld().getId());
                gruntildaPlatformDTOS.add(gruntildaPlatformDTO);
            }
            return ResponseEntity.ok(gruntildaPlatformDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
