package com.jpstechno.stock_back.securites;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;

public class MyUserPrincipal implements UserDetails {

    private CompteActeurMagasin acteurMagasin;

    public MyUserPrincipal(CompteActeurMagasin acteurMagasin) {
        this.acteurMagasin = acteurMagasin;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return List.of(new SimpleGrantedAuthority(acteurMagasin.getListeRole()));
        return null;
    }

    @Override
    public @Nullable String getPassword() {
        return acteurMagasin.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return acteurMagasin.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return acteurMagasin.getActif() && acteurMagasin.getActeur().isVerifiedEmail();
    }

    /**
     * Permet de recuperer le compte de l'acteur connecte sur le magasin
     * 
     * @return
     */
    public CompteActeurMagasin getConnetedUser() {
        return this.acteurMagasin;
    }

}
