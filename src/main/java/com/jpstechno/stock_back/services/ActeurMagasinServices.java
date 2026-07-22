package com.jpstechno.stock_back.services;

import com.jpstechno.stock_back.modeles.Roles;

public interface ActeurMagasinServices {

    void ajouterRole(long compteId, Roles role);

    void retirerRole(long compteId, Roles role);

    boolean haveRole(Long compteId, Class<? extends Roles> role);

}
