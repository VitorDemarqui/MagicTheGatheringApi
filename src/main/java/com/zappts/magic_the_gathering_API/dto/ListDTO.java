package com.zappts.magic_the_gathering_API.dto;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListDTO {
    private Integer id;

    @NotNull
    @Size(min = 1)
    private Jogador idJogador;

    @NotNull
    private Carta idCarta;

    @NotNull
    @Size(min = 1, max = 500)
    private Integer qtdCarta;
}
