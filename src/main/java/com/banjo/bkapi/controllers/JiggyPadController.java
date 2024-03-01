package com.banjo.bkapi.controllers;


import com.banjo.bkapi.models.JiggyPad;
import com.banjo.bkapi.services.JiggyPadService;
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
             /jiggyPad/{id}
             /jiggyPad/world/{id}
     */

    private final JiggyPadService jiggyPadService;

    public JiggyPadController(JiggyPadService jiggyPadService){
        this.jiggyPadService = jiggyPadService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JiggyPad> getJiggyPadById(@PathVariable Long id){
        return jiggyPadService.getJiggyPadById(id)
                .map(ResponseEntity::ok)
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
