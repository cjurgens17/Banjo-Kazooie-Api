package com.banjo.bkapi.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HoneycombDTO {
    private Long id;
    private String location;
    private Long world_id;
    private Long hub_world_id;
}
