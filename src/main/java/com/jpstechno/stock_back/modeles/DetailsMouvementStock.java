package com.jpstechno.stock_back.modeles;

import com.jpstechno.stock_back.enumerations.TypeMouvement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsMouvementStock {

    private MouvementStock idMouvement; //

    private Produits produit;

    private double quantite; // quantite mis en stock

    private double restant; // quantite du produit restant en stock apres le mouvememt

    private TypeMouvement typeMouvement;
}
