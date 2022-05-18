package com.zappts.magic_the_gathering_API.dto;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Lista;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCollectionDTO implements Serializable {
    private Integer id;

    @NotNull
    private Carta carta;

    @NotNull
    @Min(value = 1, message = "qtdCarta should not be less than 1")
    private Integer qtdCarta;

    @NotNull
    private Lista list;
}
