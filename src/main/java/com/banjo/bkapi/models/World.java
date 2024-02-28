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

    @Column(name = "jigsawsNeeded")
    private int jigsawsNeeded;

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name="jinzos")
    private List<Jinzo> jinzos;


    //ADD:
    //HoneyCombs
    //GruntildaPlatform
}
