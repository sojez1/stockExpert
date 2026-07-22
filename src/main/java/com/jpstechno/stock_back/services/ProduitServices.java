package com.jpstechno.stock_back.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jpstechno.stock_back.dto.dtoRequests.ProduitRequestDto;
import com.jpstechno.stock_back.modeles.Produits;

public interface ProduitServices {

    Produits enregistrerUnProduit(ProduitRequestDto produit);

    String ajouterUnePhoto(long productId, MultipartFile photo);

    List<Produits> listeDesproduits();
}
