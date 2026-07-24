package com.jpstechno.stock_back.serviceImplementation;

import java.util.Optional;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.dto.dtoRequests.ActeurMagasinRequestDto;
import com.jpstechno.stock_back.evenements.appEvents.CompteActeurMagasinCreationEvent;
import com.jpstechno.stock_back.exceptions.magasin.MagasinNotFound;
import com.jpstechno.stock_back.modeles.Acteurs;
import com.jpstechno.stock_back.modeles.CompteActeurMagasin;
import com.jpstechno.stock_back.modeles.Magasins;
import com.jpstechno.stock_back.modeles.Roles;
import com.jpstechno.stock_back.repositories.ActeurMagasinRepositories;
import com.jpstechno.stock_back.repositories.ActeurReposotories;
import com.jpstechno.stock_back.repositories.MagasinRepositories;
import com.jpstechno.stock_back.services.ActeurMagasinServices;

@Service
public class ActeurMagasinImplementation implements ActeurMagasinServices {

    private final ActeurMagasinRepositories acteurMagasinRepo;
    private final ActeurReposotories acteurRepo;
    private final MagasinRepositories magasinRepo;
    private final ApplicationEventPublisher eventPublisher;

    public ActeurMagasinImplementation(ActeurMagasinRepositories acteurMagasinRepo, ActeurReposotories acteurRepo,
            MagasinRepositories magasinRepo, ApplicationEventPublisher eventPublisher) {
        this.acteurMagasinRepo = acteurMagasinRepo;
        this.acteurRepo = acteurRepo;
        this.magasinRepo = magasinRepo;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CompteActeurMagasin addUserForMagasin(ActeurMagasinRequestDto acteurMagasinRequest) {

        // 1- Verification de l'existance du magasin
        Magasins magasin = magasinRepo.findById(acteurMagasinRequest.magasinId()).orElse(null);
        if (magasin == null) {
            throw new MagasinNotFound("Le magasin demande est introuvable");
        }

        // 2- Verifier si acteur existait dans le systeme, si non, le creer
        Acteurs acteur = acteurRepo.findActeurByEmail(acteurMagasinRequest.email()).orElseGet(() -> {

            Acteurs newActeur = Acteurs.builder()
                    .email(acteurMagasinRequest.email())
                    .build();
            return acteurRepo.save(newActeur);
        });

        // 3- verifier si candidat avait compte avec le magasin
        CompteActeurMagasin compte = acteurMagasinRepo.findByActeur(acteur).orElse(null);
        if (compte != null) {
            throw new RuntimeException("Vous disposez deja d'un compte avec ce commerce");
        } else {
            compte = new CompteActeurMagasin();
            compte.setActeur(acteur);
            compte.setMagasin(magasin);
            acteurMagasinRepo.save(compte);

            // 4- creer un evenement de notification a l'utilisateur
            eventPublisher.publishEvent(new CompteActeurMagasinCreationEvent(compte));

        }

        return compte;

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
