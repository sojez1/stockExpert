package com.jpstechno.stock_back.controlleurs;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.stock_back.dataTransfert.CategorieDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.serviceImplementation.TranslatorServices;
import com.jpstechno.stock_back.services.CategorieServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produits")
public class ProductControl {

    private final TranslatorServices translator;

    private final CategorieServices categorieService;

    public ProductControl(TranslatorServices translator, CategorieServices categorieService) {
        this.translator = translator;
        this.categorieService = categorieService;
    }

    @PostMapping("/save/categorie")
    public ResponseEntity<Categories> saveProduct(@Valid @RequestBody CategorieDto categorie) {
        Categories cat = categorieService.saveCategorie(categorie);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);

    }

}
