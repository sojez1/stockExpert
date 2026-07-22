package com.jpstechno.stock_back.services;

import com.jpstechno.stock_back.dto.dtoRequests.AuthenticationRequestDto;

public interface AuthenticationService {

    String login(AuthenticationRequestDto authDto);

    String refreshToken(Object attribute);

}
