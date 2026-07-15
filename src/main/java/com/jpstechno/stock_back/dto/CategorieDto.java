package com.jpstechno.stock_back.dto;

import jakarta.validation.constraints.NotBlank;

public record CategorieDto(

                @NotBlank(message = "{categories.name.notblank}") String name,

                @NotBlank(message = "") String description

) {
}
