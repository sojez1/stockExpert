/**
 * Cette classe permet de creer un objet UsernamePasswordAuthenticationToken qui prend en compte le magasin
 * Ainsi, l'authentification se fera avec le username (ou email), le password et le magasin id
 */

package com.jpstechno.stock_back.securites;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.jpstechno.stock_back.dto.dtoRequests.AuthenticationRequestDto;

public class MyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Long magasinId;

    public MyUsernamePasswordAuthenticationToken(AuthenticationRequestDto authDto) {
        super(authDto.usernameOrEmail(), authDto.password());
        this.magasinId = authDto.magasinId();
    }

    public MyUsernamePasswordAuthenticationToken(MyUserPrincipal principal, Object credentials,
            Collection<? extends GrantedAuthority> autorities, long magasinId) {
        super(principal, credentials, autorities);
        this.magasinId = magasinId;
    }

    public MyUserPrincipal getConneteduser() {
        return (MyUserPrincipal) getPrincipal();
    }

    public long getMagasinId() {
        return this.magasinId;
    }

}
