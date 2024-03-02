package com.banjo.bkapi.models;


import com.banjo.bkapi.enums.Color;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="jinzos")
public class Jinzo  extends BaseEntity{

    @Column(name="color")
    private Color color;

    @Lob
    @Column(name="location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="world", insertable = false, updatable = false)
    @JsonIgnore
    @JsonIgnoreProperties("jinzos")
    private World world;

    @Column(name = "world_id")
    private Long worldId;

    @PostLoad
    public void postLoad() {
        if (world != null) {
            worldId = world.getId();
        }
    }

}
