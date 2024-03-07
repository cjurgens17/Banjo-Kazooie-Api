package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.JinzoDTO;
import com.banjo.bkapi.enums.Color;
import com.banjo.bkapi.models.Jinzo;
import com.banjo.bkapi.services.JinzoService;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("jinzo")
@CrossOrigin
public class JinzoController {

      /*
    Controller which provides all GET requests that have to do with Banjo Kazooie Jinzo Request
            Endpoints:
             /jinzo/color/{color}
             /jinzo/id/{id}
             /jinzo/world/{id}

             ---->CacheTimes is implemented for constants of time values and duration for caching<----
     */

    private final JinzoService jinzoService;

    public JinzoController(JinzoService jinzoService){
        this.jinzoService = jinzoService;
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Jinzo>> getAllJinzoByColor(@PathVariable String color){
        Color requestedColor = Color.getColorFromString(color);

        Optional<List<Jinzo>> optionalJinzos = jinzoService.findByColor(requestedColor);

        return optionalJinzos.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JinzoDTO> getJinzoById(@PathVariable Long id){
        Optional<Jinzo> optionalJinzo = jinzoService.findById(id);

        return optionalJinzo.map(jinzo -> {
            JinzoDTO jinzoDTO = new JinzoDTO();
            jinzoDTO.setId(jinzo.getId());
            jinzoDTO.setWorld_Id(jinzo.getWorld().getId());
            jinzoDTO.setLocation(jinzo.getLocation());
            jinzoDTO.setColor(jinzo.getColor());
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY, CacheTimes.DAYS))
                    .eTag(jinzo.getLocation())
                    .body(jinzoDTO);
        })
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
