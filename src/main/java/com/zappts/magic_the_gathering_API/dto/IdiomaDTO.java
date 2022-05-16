package com.zappts.magic_the_gathering_API.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdiomaDTO {
    private Integer id;

    @NotNull
    private String name;
}
