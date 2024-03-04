package com.banjo.bkapi.controllers;


import com.banjo.bkapi.dtos.JiggyDTO;
import com.banjo.bkapi.models.Jiggy;
import com.banjo.bkapi.services.JiggyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jiggy")
@CrossOrigin
public class JiggyController {

     /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Jiggy Request
            Endpoints:
             /jiggy/id/{id}
             /jiggy/world/{id}
             /all
     */

    private final JiggyService jiggyService;

    public JiggyController(JiggyService jiggyService){
        this.jiggyService = jiggyService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JiggyDTO> getJiggyById(@PathVariable Long id){
        Optional<Jiggy> optJiggy = jiggyService.findById(id);

        return optJiggy.map(jiggy -> {
            JiggyDTO jiggyDTO = new JiggyDTO();
            jiggyDTO.setWorld_id(jiggy.getWorld().getId());
            jiggyDTO.setLocation(jiggy.getLocation());
            jiggyDTO.setHub_world_id(jiggy.getHubWorld().getId());
            jiggyDTO.setId(jiggy.getId());
            return ResponseEntity.ok(jiggyDTO);
        })
                .orElse(ResponseEntity.notFound().build());
    }



    @GetMapping("/all")
    public ResponseEntity<List<Jiggy>> getAllJiggies(){
        return jiggyService.getAllJiggies()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<List<Jiggy>> getJiggyByWorld(@PathVariable Long id){
        return jiggyService.findJiggiesByWorld(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
