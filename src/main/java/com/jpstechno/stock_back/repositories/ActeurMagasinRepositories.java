package com.jpstechno.stock_back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpstechno.stock_back.modeles.Acteurs;
import com.jpstechno.stock_back.modeles.CompteActeurMagasin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;

public interface ActeurMagasinRepositories extends JpaRepository<CompteActeurMagasin, Long> {

    @Query("SELECT cpt FROM CompteActeurMagasin cpt WHERE LOWER(cpt.acteur.email) = LOWER(:email) AND cpt.magasin.id = :id")
    Optional<CompteActeurMagasin> findByEmailAndMagasinId(String email, long magasinId);

    Optional<CompteActeurMagasin> findCompteByMagasinIdAndemail(long magasinId, String email);

    Optional<CompteActeurMagasin> findByActeur(Acteurs acteur);

}
