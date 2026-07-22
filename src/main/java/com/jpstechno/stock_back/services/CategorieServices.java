package com.jpstechno.stock_back.services;

import com.jpstechno.stock_back.dto.dtoRequests.CategorieRequestDto;
import com.jpstechno.stock_back.modeles.Categories;

public interface CategorieServices {

    Categories saveCategorie(CategorieRequestDto categorie);
}
