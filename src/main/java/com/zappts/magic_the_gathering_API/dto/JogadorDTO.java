package com.zappts.magic_the_gathering_API.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO {
    private Integer id;

    @Size(min = 1, max = 60)
    private String name;

    @Size(min = 1, max = 30)
    private String email;
}
