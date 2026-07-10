package com.jpstechno.stock_back.serviceImplementation;

import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.dataTransfert.CategorieDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.repositories.CategorieRepositories;
import com.jpstechno.stock_back.services.CategorieServices;

@Service
public class CategorieImplementation implements CategorieServices {

    private final CategorieRepositories cateRepo;

    public CategorieImplementation(CategorieRepositories cateRepo) {
        this.cateRepo = cateRepo;
    }

    @Override
    public Categories saveCategorie(CategorieDto categorieDto) {
        Categories categorie = Categories.builder()
                .name(categorieDto.name()).description(categorieDto.description())
                .build();
        return cateRepo.save(categorie);

    }

}
