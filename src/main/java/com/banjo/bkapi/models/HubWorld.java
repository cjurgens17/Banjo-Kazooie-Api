package com.banjo.bkapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Honeycomb> honeycombs;

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Door> doors;

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<JiggyPad> jiggyPads;

    @OneToMany(mappedBy = "hubWorld", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Jiggy> jiggies;

}
