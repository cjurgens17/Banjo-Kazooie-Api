package com.banjo.bkapi.controllers;


import com.banjo.bkapi.models.*;
import com.banjo.bkapi.services.HubWorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hubWorld")
public class HubWorldController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Hubworld Request
            Endpoints:
             /hubWorld/{id}
             /hubWorld/doors/{id}
             /hubWord/honeycombs/{id}
             /hubWorld/jiggies/{id}
             /hubWorld/jiggypads/{id}
     */

    private final HubWorldService hubWorldService;

    public HubWorldController(HubWorldService hubWorldService){
        this.hubWorldService = hubWorldService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HubWorld> getHubWorldById(@PathVariable Long id){
        return hubWorldService.getHubworldById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doors/{id}")
    public ResponseEntity<List<Door>> getHubWorldDoors(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        return optionalHubWorld.map(hubWorld -> ResponseEntity.ok(hubWorld.getDoors())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/honeycombs/{id}")
    public ResponseEntity<List<Honeycomb>> getHubWorldHoneycombs(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        return optionalHubWorld.map(hubWorld -> ResponseEntity.ok(hubWorld.getHoneycombs())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/jiggies/{id}")
    public ResponseEntity<List<Jiggy>> getHubWorldJiggies(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        return optionalHubWorld.map(hubWorld -> ResponseEntity.ok(hubWorld.getJiggies())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/jiggypads/{id}")
    public ResponseEntity<List<JiggyPad>> getHubWorldJiggyPads(@PathVariable Long id){
        Optional<HubWorld> optionalHubWorld = hubWorldService.getHubworldById(id);

        return optionalHubWorld.map(hubWorld -> ResponseEntity.ok(hubWorld.getJiggyPads())).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
