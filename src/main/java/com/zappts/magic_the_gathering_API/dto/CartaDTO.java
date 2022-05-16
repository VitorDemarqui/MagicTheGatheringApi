package com.zappts.magic_the_gathering_API.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CartaDTO implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 60)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    private String edition;

    @NotNull
    private Idioma idioma;

    @NotNull
    private Boolean foil;

    @NotNull
    @Size
    private Double value;
}
