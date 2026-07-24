package com.jpstechno.stock_back.securites;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;
import com.jpstechno.stock_back.repositories.ActeurMagasinRepositories;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private ActeurMagasinRepositories acteurMagasinRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        MyUsernamePasswordAuthenticationToken myAuthToken = (MyUsernamePasswordAuthenticationToken) authentication;
        String email = myAuthToken.getName();
        String password = myAuthToken.getCredentials().toString();
        long magasinId = myAuthToken.getMagasinId();

        // recherche de l'utilisateur
        CompteActeurMagasin acteurMagasin = acteurMagasinRepo.findByEmailAndMagasinId(email, magasinId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // verification du mot de passe
        if (!passwordEncoder.matches(password, acteurMagasin.getMotDePasse())) {
            throw new BadCredentialsException("mot de passe incorrect");
        }

        MyUserPrincipal principal = new MyUserPrincipal(acteurMagasin);

        return new MyUsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities(), magasinId);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MyUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
