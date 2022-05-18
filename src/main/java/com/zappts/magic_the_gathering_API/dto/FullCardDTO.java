package com.zappts.magic_the_gathering_API.dto;

import com.zappts.magic_the_gathering_API.entity.Idioma;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullCardDTO {
    private Integer id;
    private String name;
    private String edition;
    private Idioma idioma;
    private Boolean foil;
    private Double value;
    private Integer qtdCarta;
}
