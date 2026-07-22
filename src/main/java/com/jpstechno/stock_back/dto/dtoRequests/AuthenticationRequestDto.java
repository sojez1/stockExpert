package com.jpstechno.stock_back.dto.dtoRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AuthenticationRequestDto(
        @NotBlank String usernameOrEmail,
        @NotBlank String password,
        @Positive long magasinId) {

}
