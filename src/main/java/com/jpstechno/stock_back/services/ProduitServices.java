package com.jpstechno.stock_back.services;

import org.springframework.web.multipart.MultipartFile;

import com.jpstechno.stock_back.dto.ProduitRequestDto;
import com.jpstechno.stock_back.modeles.Produits;

public interface ProduitServices {

    Produits enregistrerUnProduit(ProduitRequestDto produit);

    String ajouterUnePhoto(long productId, MultipartFile photo);
}
