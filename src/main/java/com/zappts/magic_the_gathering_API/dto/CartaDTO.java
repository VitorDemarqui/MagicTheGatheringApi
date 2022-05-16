package com.zappts.magic_the_gathering_API.dto;

import com.zappts.magic_the_gathering_API.entity.Idioma;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaDTO {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 60)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    private String edition;

    @NotNull
    @Size(min = 1)
    private Idioma idIdioma;

    @NotNull
    private Boolean foil;

    @NotNull
    @Size
    private Double value;
}
