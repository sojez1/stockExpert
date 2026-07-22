package com.jpstechno.stock_back.controlleurs;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jpstechno.stock_back.dto.dtoRequests.CategorieRequestDto;
import com.jpstechno.stock_back.dto.dtoRequests.ProduitRequestDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.modeles.Produits;
import com.jpstechno.stock_back.serviceImplementation.TranslatorServices;
import com.jpstechno.stock_back.services.CategorieServices;
import com.jpstechno.stock_back.services.ProduitServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produits")
public class ProductControl {

    private final TranslatorServices translator;

    private final CategorieServices categorieService;
    private final ProduitServices produitService;

    public ProductControl(TranslatorServices translator, CategorieServices categorieService,
            ProduitServices produitService) {
        this.translator = translator;
        this.categorieService = categorieService;
        this.produitService = produitService;
    }

    @PostMapping("/categorie/save")
    public ResponseEntity<Categories> saveCategorie(@Valid @RequestBody CategorieRequestDto categorie) {
        Categories cat = categorieService.saveCategorie(categorie);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);

    }

    @PostMapping("/produit/enregistrer")
    public ResponseEntity<?> saveProduit(@Valid @RequestBody ProduitRequestDto produitData) {
        Produits prod = produitService.enregistrerUnProduit(produitData);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> listeDesProduits() {
        List<Produits> liste = produitService.listeDesproduits();
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }

    @PostMapping("/produit/{id}/photo/add")
    public ResponseEntity<?> addPhotoToProduct(long productId, MultipartFile photo) {
        String result = produitService.ajouterUnePhoto(productId, photo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
