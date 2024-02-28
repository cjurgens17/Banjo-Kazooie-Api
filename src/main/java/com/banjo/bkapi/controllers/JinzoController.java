package com.banjo.bkapi.controllers;


import com.banjo.bkapi.enums.Color;
import com.banjo.bkapi.models.Jinzo;
import com.banjo.bkapi.services.JinzoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jinzo")
@CrossOrigin
public class JinzoController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Jinzo Request
            Endpoints:
             /jinzo/{color}
             /jinzo/{id}
             /jinzo/world/{id}
     */

    private final JinzoService jinzoService;

    public JinzoController(JinzoService jinzoService){
        this.jinzoService = jinzoService;
    }

    @GetMapping("/{color}")
    public ResponseEntity<List<Jinzo>> getAllJinzoByColor(@PathVariable String color){
        Color requestedColor = Color.getColorFromString(color);

        Optional<List<Jinzo>> optionalJinzos = jinzoService.findByColor(requestedColor);

        return optionalJinzos.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jinzo> getJinzoById(@PathVariable Long id){
        Optional<Jinzo> optionalJinzo = jinzoService.findById(id);

        return optionalJinzo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //need to add world entity first
    @GetMapping("/world/{id}")
    public ResponseEntity<List<Jinzo>> getJinzoByWorldId(@PathVariable Long id){
        Optional<List<Jinzo>> optionalJinzos = jinzoService.findByWorldId(id);

        return optionalJinzos.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
