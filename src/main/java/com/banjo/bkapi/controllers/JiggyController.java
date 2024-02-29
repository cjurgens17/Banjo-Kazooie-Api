package com.banjo.bkapi.controllers;


import com.banjo.bkapi.models.Jiggy;
import com.banjo.bkapi.services.JiggyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jiggy")
@CrossOrigin
public class JiggyController {

     /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Jinzo Request
            Endpoints:
             /jiggy/{id}
             /jiggy/world/{id}
             /all
     */

    private final JiggyService jiggyService;

    public JiggyController(JiggyService jiggyService){
        this.jiggyService = jiggyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jiggy> getJiggyById(@PathVariable Long id){
        return jiggyService.findById(id)
                .map(ResponseEntity::ok)
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
