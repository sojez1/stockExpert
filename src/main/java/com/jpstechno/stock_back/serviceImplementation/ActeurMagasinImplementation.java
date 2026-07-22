package com.jpstechno.stock_back.serviceImplementation;

import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;
import com.jpstechno.stock_back.modeles.Roles;
import com.jpstechno.stock_back.repositories.ActeurMagasinRepositories;
import com.jpstechno.stock_back.services.ActeurMagasinServices;

@Service
public class ActeurMagasinImplementation implements ActeurMagasinServices {

    private final ActeurMagasinRepositories acteurMagasinRepo;

    public ActeurMagasinImplementation(ActeurMagasinRepositories acteurMagasinRepo) {
        this.acteurMagasinRepo = acteurMagasinRepo;
    }

    @Override
    public void ajouterRole(long compteId, Roles role) {
        CompteActeurMagasin compte = compteActeurMagasin(compteId);
        compte.getListeRole().add(role);
        acteurMagasinRepo.save(compte);
    }

    @Override
    public void retirerRole(long compteId, Roles role) {
        CompteActeurMagasin compte = compteActeurMagasin(compteId);
        compte.getListeRole().remove(role);
        acteurMagasinRepo.save(compte);
    }

    @Override
    public boolean haveRole(Long compteId, Class<? extends Roles> role) {
        CompteActeurMagasin compte = compteActeurMagasin(compteId);
        return compte.getListeRole().stream()
                .anyMatch(role::isInstance);

    }

    private CompteActeurMagasin compteActeurMagasin(long compteId) {
        return acteurMagasinRepo.findById(compteId).orElseThrow(() -> new RuntimeException("Compte non trouve"));

    }

}
