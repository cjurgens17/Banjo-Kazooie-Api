package com.banjo.bkapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Column(name="jinzos")
    private List<Jinzo> jinzos;

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name="honeycombs")
    private List<Honeycomb> honeycombs;

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "jiggies")
    private List<Jiggy> jiggies;


}
