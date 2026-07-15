package com.jpstechno.stock_back.dataTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.jpstechno.stock_back.enumerations.Unites;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.modeles.Produits;
import com.jpstechno.stock_back.repositories.CategorieRepositories;
import com.jpstechno.stock_back.repositories.ProduitRepositories;

@DataJpaTest
public class ProduitJpaTest {

    @Autowired
    private ProduitRepositories prodRepo;

    @Autowired
    private CategorieRepositories cateRepo;

    @Test
    public void addProduitTest() {

        // arrange:
        Categories cateprod = cateRepo.save(
                Categories.builder()
                        .name("Informatique")
                        .description("Consommables informatiques")
                        .build());

        Produits prod1 = Produits.builder()
                .reference("AZ123")
                .upc("22900101")
                .designation("Imprimante")
                .unite(Unites.PIECE)
                .categorie(cateprod)
                .prixUnitaire(new BigDecimal(205.00))
                .build();
        // act
        Produits savedproduct = prodRepo.save(prod1);

        // assert
        assertNotNull(savedproduct);
        assertNotNull(savedproduct.getId());
        assertEquals(prod1.getPrixUnitaire(), savedproduct.getPrixUnitaire());
        assertEquals(cateprod.getId(), savedproduct.getCategorie().getId());
        assertTrue(savedproduct.isActif());
    }

}
