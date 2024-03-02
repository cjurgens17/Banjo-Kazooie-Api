package com.banjo.bkapi.models;
import com.banjo.bkapi.services.HoneycombService;
import com.banjo.bkapi.services.JiggyService;
import com.banjo.bkapi.services.JinzoService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "worlds")
public class World extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "jigsaws_needed")
    private int jigsawsNeeded;

    @OneToOne
    @JoinColumn(name = "jiggy_pad_id")
    private JiggyPad jiggyPad;

    @OneToOne
    @JoinColumn(name = "gruntilda_platform_id")
    private GruntildaPlatform gruntildaPlatform;

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jinzo> jinzos = new ArrayList<>();

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Honeycomb> honeycombs = new ArrayList<>();

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jiggy> jiggies = new ArrayList<>();



    //maybe we have to postLoad these??????

//    @PostLoad
//    public void postLoad(){
//        jinzos = jinzoService.findAllJinzos().stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//
//        honeycombs = honeycombService.findAllHoneycombs().stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//
//        jiggies = jiggyService.getAllJiggies().stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//    }
}
