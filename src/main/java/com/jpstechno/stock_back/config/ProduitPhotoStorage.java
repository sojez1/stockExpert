package com.jpstechno.stock_back.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "stockage.fichier.photos.produits")
@Component
@Getter
@Setter
public class ProduitPhotoStorage {

    private String produitPhotoStorage;

}
