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
public class DeckDTO {
    private Integer id;

    @NotNull
    private Jogador jogador;

    @NotNull
    private Carta carta;

    @NotNull
    @Size(min = 1, max = 500)
    private Integer qtd;
}
