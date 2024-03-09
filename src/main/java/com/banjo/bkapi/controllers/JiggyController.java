package com.banjo.bkapi.controllers;


import com.banjo.bkapi.cacheControl.CacheTimes;
import com.banjo.bkapi.dtos.JiggyDTO;
import com.banjo.bkapi.models.Jiggy;
import com.banjo.bkapi.services.JiggyService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(CacheTimes.SINGLE_ENTITY,CacheTimes.DAYS))
                    .eTag(jiggy.getLocation())
                    .body(jiggyDTO);
        })
                .orElse(ResponseEntity.notFound().build());
    }



    @GetMapping("/all")
    public ResponseEntity<List<JiggyDTO>> getAllJiggies(){
        Optional<List<Jiggy>> optJiggies = jiggyService.getAllJiggies();

        if(optJiggies.isPresent()){
            List<Jiggy> jiggies = optJiggies.get();
            List<JiggyDTO> jiggyDTOS = new ArrayList<>();

            for(Jiggy jiggy : jiggies){
                JiggyDTO jiggyDTO = new JiggyDTO();
                jiggyDTO.setId(jiggy.getId());
                jiggyDTO.setLocation(jiggy.getLocation());
                jiggyDTO.setWorld_id(jiggy.getWorld().getId());
                jiggyDTO.setHub_world_id(jiggy.getHubWorld().getId());
                jiggyDTOS.add(jiggyDTO);
            }
            return ResponseEntity.ok(jiggyDTOS);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<List<JiggyDTO>> getJiggyByWorld(@PathVariable Long id){
      Optional<List<Jiggy>> optJiggies = jiggyService.findJiggiesByWorld(id);

      if(optJiggies.isPresent()){
          List<Jiggy> jiggies = optJiggies.get();
          List<JiggyDTO> jiggyDTOS = new ArrayList<>();

          for(Jiggy jiggy : jiggies){
              JiggyDTO jiggyDTO = new JiggyDTO();
              jiggyDTO.setId(jiggy.getId());
              jiggyDTO.setLocation(jiggy.getLocation());
              jiggyDTO.setWorld_id(jiggy.getWorld().getId());
              jiggyDTO.setHub_world_id(jiggy.getHubWorld().getId());
              jiggyDTOS.add(jiggyDTO);
          }
          return ResponseEntity.ok(jiggyDTOS);
      }else {
          return ResponseEntity.notFound().build();
      }
    }
}
