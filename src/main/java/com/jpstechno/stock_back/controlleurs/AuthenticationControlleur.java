package com.jpstechno.stock_back.controlleurs;

import java.time.Duration;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.stock_back.dto.dtoRequests.AuthenticationRequestDto;
import com.jpstechno.stock_back.securites.JwtService;
import com.jpstechno.stock_back.securites.MyUserPrincipal;
import com.jpstechno.stock_back.securites.MyUsernamePasswordAuthenticationToken;
import com.jpstechno.stock_back.services.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authentification")
public class AuthenticationControlleur {

    private final AuthenticationService authService;

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    public AuthenticationControlleur(AuthenticationService authService, AuthenticationManager authManager,
            JwtService jwtService) {
        this.authService = authService;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto loginData) {

        Authentication auth = authManager.authenticate(new MyUsernamePasswordAuthenticationToken(loginData));

        if (auth.isAuthenticated()) {
            MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
            String tokenJwt = jwtService.generateAccessToken(principal.getConnetedUser());

            ResponseCookie cookie = ResponseCookie
                    .from("accees_token", tokenJwt)
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .path("/")
                    .maxAge(Duration.ofMinutes(15))
                    .build();

            return ResponseEntity.ok()
                    .header(org.springframework.http.HttpHeaders.SET_COOKIE,
                            cookie.toString())
                    .body(Map.of("message", "Connexion reussie"));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    public ResponseEntity<?> refreshToken(HttpServletRequest httpRequest) {
        String refreshToken = authService.refreshToken(httpRequest.getAttribute("authorization"));

        String newAccessToken = "";

        ResponseCookie cookie = ResponseCookie
                .from("accees_token", newAccessToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofMinutes(15))
                .build();

        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.SET_COOKIE,
                        cookie.toString())
                .build();

    }

}
