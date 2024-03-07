package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.JiggyPadDTO;
import com.banjo.bkapi.models.JiggyPad;
import com.banjo.bkapi.services.JiggyPadService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("jiggyPad")
@CrossOrigin
public class JiggyPadController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie JiggyPad Request
            Endpoints:
             /jiggyPad/id/{id}
             /jiggyPad/world/{id}
     */

    private final JiggyPadService jiggyPadService;

    public JiggyPadController(JiggyPadService jiggyPadService){
        this.jiggyPadService = jiggyPadService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JiggyPadDTO> getJiggyPadById(@PathVariable Long id){
        Optional<JiggyPad> optJiggyPad = jiggyPadService.getJiggyPadById(id);
                return optJiggyPad.map(jiggyPad -> {
                    JiggyPadDTO jiggyPadDTO = new JiggyPadDTO();
                    jiggyPadDTO.setWorld_id(jiggyPad.getWorld().getId());
                    jiggyPadDTO.setHub_world_id(jiggyPad.getHubWorld().getId());
                    jiggyPadDTO.setLocation(jiggyPad.getLocation());
                    jiggyPadDTO.setId(jiggyPad.getId());
                    return ResponseEntity.ok()
                            .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                            .eTag(jiggyPad.getLocation())
                            .body(jiggyPadDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<JiggyPad> getJiggyPadByWorld(@PathVariable Long id){
        Optional<List<JiggyPad>> optJiggyPads = jiggyPadService.getAllJiggyPads();

        if(optJiggyPads.isPresent()){
            return optJiggyPads
                    .flatMap(jiggyPads -> jiggyPads.stream()
                            .filter(jiggyPad -> Objects.equals(jiggyPad.getWorld().getId(), id))
                            .findFirst()
                            .map(ResponseEntity::ok))
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }
}
