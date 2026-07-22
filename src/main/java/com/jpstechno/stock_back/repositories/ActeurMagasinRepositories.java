package com.jpstechno.stock_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;

public interface ActeurMagasinRepositories extends JpaRepository<CompteActeurMagasin, Long> {

}
