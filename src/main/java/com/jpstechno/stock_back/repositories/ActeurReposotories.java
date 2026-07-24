package com.jpstechno.stock_back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.stock_back.modeles.Acteurs;

import jakarta.validation.constraints.NotBlank;

public interface ActeurReposotories extends JpaRepository<Acteurs, Long> {

    Optional<Acteurs> findActeurByEmail(String email);

}
