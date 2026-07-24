package com.jpstechno.stock_back.dto.dtoRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ActeurMagasinRequestDto(
        @Positive long magasinId,
        @NotBlank String email) {

}
