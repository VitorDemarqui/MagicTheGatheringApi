package com.zappts.magic_the_gathering_API.dto;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeckDTO {
    private Integer id;

    @NotNull
    @Size(min = 1)
    private Jogador idJogador;

    @NotNull
    @Size(min = 1)
    private Carta idCarta;

    @NotNull
    @Size(min = 1, max = 500)
    private Integer qtd;
}
