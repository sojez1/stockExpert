package com.jpstechno.stock_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.stock_back.modeles.Categories;

public interface CategorieRepositories extends JpaRepository<Categories, Long> {

}
