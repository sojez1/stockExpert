package com.jpstechno.stock_back.services;

import com.jpstechno.stock_back.dto.dtoRequests.ActeurMagasinRequestDto;
import com.jpstechno.stock_back.modeles.CompteActeurMagasin;
import com.jpstechno.stock_back.modeles.Roles;

public interface ActeurMagasinServices {

    CompteActeurMagasin addUserForMagasin(ActeurMagasinRequestDto acteurMagasinRequest);

    void ajouterRole(long compteId, Roles role);

    void retirerRole(long compteId, Roles role);

    boolean haveRole(Long compteId, Class<? extends Roles> role);

}
