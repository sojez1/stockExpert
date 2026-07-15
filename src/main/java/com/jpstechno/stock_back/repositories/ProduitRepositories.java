package com.jpstechno.stock_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.stock_back.modeles.Produits;

public interface ProduitRepositories extends JpaRepository<Produits, Long> {

}
