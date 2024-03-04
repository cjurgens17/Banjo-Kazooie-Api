package com.banjo.bkapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jiggyPad")
public class JiggyPad extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(
            name = "world_id",
            foreignKey = @ForeignKey(name = "fk_world_id")
    )
    private World world;

    @ManyToOne
    @JoinColumn(
            name = "hub_world_id",
            foreignKey = @ForeignKey(name = "fk_hub_world_id")
    )
    @JsonBackReference
    private HubWorld hubWorld;
}
