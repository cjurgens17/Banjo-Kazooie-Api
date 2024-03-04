package com.banjo.bkapi.dtos;

import com.banjo.bkapi.enums.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class JinzoDTO {
    private Long id;
    private Long world_Id;
    private String location;
    private Color color;
}
