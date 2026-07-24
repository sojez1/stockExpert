package com.jpstechno.stock_back.securites;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;
import com.jpstechno.stock_back.repositories.ActeurMagasinRepositories;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ActeurMagasinRepositories acteurMagasinRepo;

    public JwtFilter(JwtService jwtService, ActeurMagasinRepositories acteurMagasinRepo) {
        this.jwtService = jwtService;
        this.acteurMagasinRepo = acteurMagasinRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Recuperation du endpoint contenu dans la requete
        String endpoint = request.getRequestURI();

        // exempter les endpoints public du filtre jwt
        if (Arrays.stream(SecurityParams.publicEndpoints).anyMatch(endpoint::equals)) {
            filterChain.doFilter(request, response);
        }

        String bearerToken = request.getHeader("authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            long acteurMagasinId = jwtService.getActeurMagasinIdFromToken(token);
            Optional<CompteActeurMagasin> compte = acteurMagasinRepo.findById(acteurMagasinId);
            if (compte.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
                MyUserPrincipal principal = new MyUserPrincipal(compte.get());
                MyUsernamePasswordAuthenticationToken authUser = new MyUsernamePasswordAuthenticationToken(principal,
                        null, principal.getAuthorities(), acteurMagasinId);
                authUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authUser);
            }

        }

        // passer au filtre suivant pour tout le reste (car pas bearer token)
        filterChain.doFilter(request, response);

    }
}
