package com.banjo.bkapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hubWorld")
public class HubWorld extends BaseEntity{

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "honeycombs")
    private List<Honeycomb> honeycombs;

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "doors")
    private List<Door> doors;

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name = "jiggy_pads")
    private List<JiggyPad> jiggyPads;

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name = "jiggies")
    private List<Jiggy> jiggies;

}
