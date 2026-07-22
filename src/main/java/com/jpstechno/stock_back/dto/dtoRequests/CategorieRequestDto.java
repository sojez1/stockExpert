package com.jpstechno.stock_back.dto.dtoRequests;

import jakarta.validation.constraints.NotBlank;

public record CategorieRequestDto(

                @NotBlank(message = "{categories.name.notblank}") String name,

                @NotBlank(message = "") String description

) {
}
