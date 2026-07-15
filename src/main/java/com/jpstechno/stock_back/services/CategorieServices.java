package com.jpstechno.stock_back.services;

import com.jpstechno.stock_back.dto.CategorieDto;
import com.jpstechno.stock_back.modeles.Categories;

public interface CategorieServices {

    Categories saveCategorie(CategorieDto categorie);
}
