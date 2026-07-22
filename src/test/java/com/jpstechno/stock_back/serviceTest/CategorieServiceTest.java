package com.jpstechno.stock_back.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpstechno.stock_back.dto.dtoRequests.CategorieRequestDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.repositories.CategorieRepositories;
import com.jpstechno.stock_back.serviceImplementation.CategorieImplementation;

@ExtendWith(MockitoExtension.class)
public class CategorieServiceTest {

    @Mock
    private CategorieRepositories cateRepo;

    @InjectMocks
    private CategorieImplementation cateService;

    @Test
    public void categorieServiceSaveTest() {
        // Arrange - preparation des donnees
        CategorieRequestDto cateDto = new CategorieRequestDto("Produits chimiques",
                "Produits chimiques destinees a l'entretien menager");
        Categories cat = Categories.builder()
                .name(cateDto.name())
                .description(cateDto.description())
                .build();

        when(cateRepo.save(any(Categories.class))).thenReturn(cat);

        // Act - appel du service
        Categories categorieSaved = cateService.saveCategorie(cateDto);

        // Assert - verification du resultat
        assertNotNull(categorieSaved);
        assertEquals(cat.getName(), categorieSaved.getName());

        // verify - verifier les interactions avec le repository
        verify(cateRepo, times(1)).save(any(Categories.class));

    }

}
