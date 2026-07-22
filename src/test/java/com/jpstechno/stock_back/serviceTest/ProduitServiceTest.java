package com.jpstechno.stock_back.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpstechno.stock_back.dto.dtoRequests.ProduitRequestDto;
import com.jpstechno.stock_back.enumerations.Unites;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.modeles.Produits;
import com.jpstechno.stock_back.repositories.CategorieRepositories;
import com.jpstechno.stock_back.repositories.ProduitRepositories;
import com.jpstechno.stock_back.serviceImplementation.ProduitImplementation;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test du service Produit")
public class ProduitServiceTest {

    @Mock
    private ProduitRepositories prodRepo;

    @Mock
    private CategorieRepositories cateRepo;

    @InjectMocks
    private ProduitImplementation prodServ;

    @Test
    @DisplayName("add product - categorie existe et reference unique")
    public void ajouterProduit() {
        // arrange
        ProduitRequestDto prodRequest = new ProduitRequestDto("AZ123", "22822814", "Ordinateur imac", 1L, Unites.PIECE,
                new BigDecimal(158.97),
                true);
        Categories cat = Categories.builder().name("Bureautique").description("Articles pour bureau").build();
        when(cateRepo.findById(prodRequest.categorieId())).thenReturn(Optional.of(cat));

        Produits prod = Produits.builder()
                .categorie(cat)
                .reference(prodRequest.reference())
                .upc(prodRequest.upc())
                .designation(prodRequest.designation())
                .unite(prodRequest.unite())
                .prixUnitaire(prodRequest.prixUnitaire())
                .taxable(prodRequest.taxable())
                .build();
        when(prodRepo.save(any(Produits.class))).thenReturn(prod);

        // act
        Produits savedProduct = prodServ.enregistrerUnProduit(prodRequest);

        // assert
        assertNotNull(savedProduct);
        assertEquals(prod.getReference(), savedProduct.getReference());

        // verify
        verify(cateRepo, times(1)).findById(1L);
        verify(prodRepo, times(1)).save(any(Produits.class));

    }

}
