package com.banjo.bkapi.models;

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
@Table(name = "gruntildaPlatform")
public class GruntildaPlatform extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(name = "world_id")
    private World world;
}
