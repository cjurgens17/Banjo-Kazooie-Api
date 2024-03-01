package com.banjo.bkapi.controllers;


import com.banjo.bkapi.models.Door;
import com.banjo.bkapi.services.DoorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("door")
@CrossOrigin
public class DoorController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Door Request
            Endpoints:
             /door/{id}
     */

    private final DoorService doorService;

    public DoorController(DoorService doorService){
        this.doorService = doorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Door> getDoorById(@PathVariable Long id){
        return doorService.getDoorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
