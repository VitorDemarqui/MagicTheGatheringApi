package com.zappts.magic_the_gathering_API.dto;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeckDTO {
    private Integer id;

    @NotNull
    private Jogador jogador;

    @NotNull
    private Carta carta;

    @NotNull
    @Min(value = 1, message = "qtdCarta should not be less than 1")
    private Integer qtd;
}
