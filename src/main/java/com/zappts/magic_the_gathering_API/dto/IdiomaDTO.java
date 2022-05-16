package com.zappts.magic_the_gathering_API.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdiomaDTO {
    private Integer id;

    @NotNull
    private String name;
}
