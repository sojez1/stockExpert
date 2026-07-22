package com.jpstechno.stock_back.serviceImplementation;

import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.dto.dtoRequests.AuthenticationRequestDto;
import com.jpstechno.stock_back.services.AuthenticationService;

@Service
public class AuthenticationImplementation implements AuthenticationService {

    @Override
    public String login(AuthenticationRequestDto authDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public String refreshToken(Object attribute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }

}
