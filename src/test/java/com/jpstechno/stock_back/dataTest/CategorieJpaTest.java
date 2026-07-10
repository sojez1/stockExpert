package com.jpstechno.stock_back.dataTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.repositories.CategorieRepositories;

@DataJpaTest
public class CategorieJpaTest {

    @Autowired
    private CategorieRepositories cateRepo;

    @Test
    @DisplayName("test - enregistrement d'une categorie")
    public void testSaveCategorie() {
        // Arrange: preparation des donnees
        Categories cat1 = Categories.builder()
                .name("produit laitier")
                .description("produits laitiers")
                .build();

        // Act:
        Categories savedCategorie = cateRepo.save(cat1);

        // Assertions
        assertNotNull(savedCategorie);
        assertEquals(cat1.getName(), savedCategorie.getName());
        assertEquals(1, cateRepo.count());
    }

}
