package com.banjo.bkapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdJavaType;
import org.hibernate.annotations.CollectionIdType;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "worlds")
public class World extends BaseEntity {

    @Column(name = "name", length = 35)
    private String name;

    @Column(name = "jigsaws_needed")
    private int jigsawsNeeded;

    @OneToOne
    @JoinColumn(name = "jiggy_pad_id")
    private JiggyPad jiggyPad;

    @OneToOne
    @JoinColumn(name = "gruntilda_platform_id")
    private GruntildaPlatform gruntildaPlatform;

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Jinzo> jinzos = new ArrayList<>();

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Honeycomb> honeycombs = new ArrayList<>();

    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Jiggy> jiggies = new ArrayList<>();
}
